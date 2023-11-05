package Mapping;

import java.util.HashSet;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

import Elements.Elemental;
import Enums.Directions;
import Enums.Types;
import Tokens.Token;

public class Map {
    public int SizeX;
    public int SizeY;
    private Box[][] mapInfo;

    public Map(int sizeX, int sizeY) {
        SizeX = sizeX;
        SizeY = sizeY;
        mapInfo = new Box[SizeX][SizeY];
        for (int x = 0; x < SizeX; x++) {
            for (int y = 0; y < SizeY; y++) {
                mapInfo[x][y] = initiateBox(x, y);
            }
        }
        obstacleGeneration();
    }

    public Box[][] getMapInfo() {
        return mapInfo;
    }

    private Box initiateBox(int x, int y) {
        if (x < safeZoneSize()[0] && y < safeZoneSize()[1]) {
            return new SafeBox(x, y, Types.AIR);
        } else if (x > (SizeX - safeZoneSize()[0] - 1) && y < safeZoneSize()[1]) {
            return new SafeBox(x, y, Types.EAU);
        } else if (x < safeZoneSize()[0] && y > (SizeY - safeZoneSize()[1] - 1)) {
            return new SafeBox(x, y, Types.FEU);
        } else if (x > (SizeX - safeZoneSize()[0] - 1) && y > (SizeY - safeZoneSize()[1] - 1)) {
            return new SafeBox(x, y, Types.TERRE);
        }
        return new Box(x, y);
    }

    private int[] safeZoneSize() {
        int safeZoneSizeX = SizeX / 4;
        int safeZoneSizeY = SizeY / 4;

        int[] safeZoneSize = {safeZoneSizeX, safeZoneSizeY};
        return safeZoneSize;
    }

    private void obstacleGeneration() {
        Random r = new Random();
        int int_random = r.nextInt((SizeX * SizeY) / 25); 
        for (int obstacle = 0; obstacle < int_random; obstacle++) {
            int coordonateX_random = r.nextInt(SizeX);
            int coordonateY_random = r.nextInt(SizeY);
            while (mapInfo[coordonateX_random][coordonateY_random].getClass() == SafeBox.class || mapInfo[coordonateX_random][coordonateY_random].isBlockedByObstacle()) {
                coordonateX_random = r.nextInt(SizeX);
                coordonateY_random = r.nextInt(SizeY);
            }
            mapInfo[coordonateX_random][coordonateY_random].setObstacle();
        }
    }

    public void printMap() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
        final String ANSI_RED = "\033[0;31m";
        final String ANSI_GREEN = "\033[0;32m";
        final String ANSI_BLUE = "\033[0;34m";
        final String ANSI_PURPLE = "\033[0;35m";

        for (int x = 0; x < SizeX + 2; x++) {
            if (x == 0) {
                System.out.print("┌");
            } else if (x == SizeX + 1) {
                System.out.println("┐");
            } else {
                System.out.print("─");
            }
        }
        for (int y = 0; y < SizeY; y++) {
            for (int x = 0; x < SizeX; x++) {
                if (x == 0) {
                    System.out.print("│");
                }
                if (mapInfo[x][y].getClass() == SafeBox.class) {
                    switch (((SafeBox) mapInfo[x][y]).getType()) {
                        case FEU:
                            if (((SafeBox) mapInfo[x][y]).isOccupiedByMaster())
                                System.out.print(ANSI_RED_BACKGROUND + "M" + ANSI_RESET);
                            else
                            System.out.print(ANSI_RED_BACKGROUND + " " + ANSI_RESET);
                            break;
                        case EAU:
                            if (((SafeBox) mapInfo[x][y]).isOccupiedByMaster())
                                System.out.print(ANSI_BLUE_BACKGROUND + "M" + ANSI_RESET);
                            else
                            System.out.print(ANSI_BLUE_BACKGROUND + " " + ANSI_RESET);
                            break;
                        case AIR:
                                if (((SafeBox) mapInfo[x][y]).isOccupiedByMaster())
                                System.out.print(ANSI_PURPLE_BACKGROUND + "M" + ANSI_RESET);
                            else
                            System.out.print(ANSI_PURPLE_BACKGROUND + " " + ANSI_RESET);
                            break;
                        case TERRE:
                                if (((SafeBox) mapInfo[x][y]).isOccupiedByMaster())
                                System.out.print(ANSI_GREEN_BACKGROUND + "M" + ANSI_RESET);
                            else
                            System.out.print(ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);
                            break;
                    }
                } else if (mapInfo[x][y].isOccupiedByToken()) {
                    switch (mapInfo[x][y].getToken().Type) {
                        case FEU:
                            System.out.print(ANSI_RED + mapInfo[x][y].getToken().getLetterForMapDisplay() + ANSI_RESET);
                            break;
                        case EAU:
                            System.out.print(ANSI_BLUE + mapInfo[x][y].getToken().getLetterForMapDisplay() + ANSI_RESET);
                            break;
                        case AIR:
                            System.out.print(ANSI_PURPLE + mapInfo[x][y].getToken().getLetterForMapDisplay() + ANSI_RESET);
                            break;
                        case TERRE:
                            System.out.print(ANSI_GREEN + mapInfo[x][y].getToken().getLetterForMapDisplay() + ANSI_RESET);
                            break;
                    }
                } else if (mapInfo[x][y].isBlockedByObstacle()) {
                    System.out.print("/");

                } else {
                    System.out.print(" ");
                }

                if (x == (SizeX - 1)) {
                    System.out.println("│");
                }
            }
        }
        for (int x = 0; x < SizeX + 2; x++) {
            if (x == 0) {
                System.out.print("└");
            } else if (x == SizeX + 1) {
                System.out.println("┘");
            } else {
                System.out.print("─");
            }
        }
    }

    private Directions getDirection(int baseX, int baseY, int newX, int newY) {
        if (baseX > newX) {
            if (baseY > newY) {
                return Directions.NW;
            } else if (baseY == newY) {
                return Directions.W;
            } else {
                return Directions.SW;
            }
        } else if (baseX == newX) {
            if (baseY > newY) {
                return Directions.N;
            } else if (baseY < newY) {
                return Directions.S; 
            }
        } else if (baseX < newX) {
            if (baseY > newY) {
                return Directions.NE;
            } else if (baseY == newY) {
                return Directions.E;
            } else {
                return Directions.SE;
            }
        }
        return null;
    }

    public List<Directions> availableTiles(int coordonateX, int coordonateY) {
        List<Directions> availableTiles = new ArrayList<Directions>();
        for (int x = coordonateX - 1; x <= coordonateX + 1; x++) {
            for (int y = coordonateY - 1; y <= coordonateY + 1; y++) {
                if (x < SizeX && y < SizeY && x >= 0 && y >= 0) {
                    if (!mapInfo[x][y].isBlockedByObstacle() && !(x == coordonateX && y == coordonateY)) {
                        availableTiles.add(getDirection(coordonateX, coordonateY, x, y));
                    }
                }
            }
        }
        return availableTiles;
    }

public HashSet<String> getBoxesFromMySafeZone(Types type) {
        HashSet<String> safeZone = new HashSet<>();
        String values;

        for (int i = 0; i < mapInfo.length; i++) {
            for (int j = 0; j < mapInfo[0].length; j++) {
                if (mapInfo[i][j] instanceof SafeBox && ((SafeBox) mapInfo[i][j]).getType() == type) {
                    values = i + "," + j;
                    safeZone.add(values);
                }
            }
        }

        return safeZone;
    }

    public void setMaster(int coordinateX, int coordinateY, Elemental master) {
        ((SafeBox) mapInfo[coordinateX][coordinateY]).setMaster(master);
    }

    public void setOccupied(int coordinateX, int coordinateY, boolean value, Token token) {
        mapInfo[coordinateX][coordinateY].setOccupied(value, token);
    }
    //////////////////////////////////////////////////////////// Path Finder to Safe Zone(inspired by A*)

    private class Node{
        int coordonateX;
        int coordonateY;
        int gCost;
        int hCost;
        int fCost;
        private Node(int x, int y, int gCost, int hCost, int fCost){
            this.coordonateX = x;
            this.coordonateY = y;
            this.gCost = gCost;
            this.hCost = hCost;
            this.fCost = fCost;
        }
    }

    private int getCost(Node node, Node originNode, Node goalNode){
        // g cost
        int distanceX = Math.abs(node.coordonateX - originNode.coordonateX);
        int distanceY = Math.abs(node.coordonateY - originNode.coordonateY);
        node.gCost = distanceX + distanceY;

        // h cost
        distanceX = Math.abs(node.coordonateX - goalNode.coordonateX);
        distanceY = Math.abs(node.coordonateY - goalNode.coordonateY); 
        node.hCost = distanceX + distanceY;

        // f cost
        node.fCost = node.gCost + node.hCost;
        return node.hCost;
    }

    public Directions safeZonePathFinder(int coordonateX, int coordonateY, Types type){
        SafeBox goal = null;
        // which safe zone
        switch(type){
            case FEU:
                goal = (SafeBox) mapInfo[0][SizeY - 1];
                break;
            case TERRE:
                goal = (SafeBox) mapInfo[SizeX - 1][SizeY - 1];
                break;
            case EAU:
                goal = (SafeBox) mapInfo[SizeX - 1][0];
                break;
            case AIR:
                goal = (SafeBox) mapInfo[0][0];
                break;
        }
        Node[][] nodeInfos;
        nodeInfos = new Node[SizeX][SizeY];
        for(int x = 0; x < SizeX; x++){
            for(int y = 0; y < SizeY; y++){
                nodeInfos[x][y] = new Node(x, y, 1000, 1000, 1000);
            }
        }
        for(int x = coordonateX - 1; x < coordonateX + 2; x++){
            for(int y = coordonateY - 1; y < coordonateY + 2; y++){
                if(!(x == coordonateX && y == coordonateY) && x < SizeX && y < SizeY && x >= 0 && y >= 0){
                    if(mapInfo[x][y].isBlockedByObstacle() == false){
                        getCost(nodeInfos[x][y], nodeInfos[coordonateX][coordonateY], nodeInfos[goal.CoordonateX][goal.CoordonateY]);
                    }    
                }
            }
        }
        Node bestNode = nodeInfos[coordonateX][coordonateY];
        for(int x = coordonateX - 1; x <= coordonateX + 1; x++){
            for(int y = coordonateY - 1; y <= coordonateY + 1; y++){
                if(x < SizeX && y < SizeY && x >= 0 && y >= 0){
                    if(nodeInfos[x][y].fCost <= bestNode.fCost){
                        bestNode = nodeInfos[x][y];
                    }
                }
                
            }
        }
        return getDirection(coordonateX, coordonateY, bestNode.coordonateX, bestNode.coordonateY);
    }
}