package Mapping;

import java.util.HashSet;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

import Elements.Master;
import Enums.Directions;
import Enums.Types;
import Tokens.Token;

public class Map {
    public int sizeX;
    public int sizeY;
    private Box[][] mapInfo;

    public Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        mapInfo = new Box[this.sizeX][this.sizeY];
        for (int x = 0; x < this.sizeX; x++) {
            for (int y = 0; y < this.sizeY; y++) {
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
        } else if (x > (sizeX - safeZoneSize()[0] - 1) && y < safeZoneSize()[1]) {
            return new SafeBox(x, y, Types.EAU);
        } else if (x < safeZoneSize()[0] && y > (sizeY - safeZoneSize()[1] - 1)) {
            return new SafeBox(x, y, Types.FEU);
        } else if (x > (sizeX - safeZoneSize()[0] - 1) && y > (sizeY - safeZoneSize()[1] - 1)) {
            return new SafeBox(x, y, Types.TERRE);
        }
        return new Box(x, y);
    }

    private int[] safeZoneSize() {
        int safeZoneSizeX = sizeX / 4;
        int safeZoneSizeY = sizeY / 4;

        return new int[]{safeZoneSizeX, safeZoneSizeY};
    }

    private void obstacleGeneration() {
        Random rand = new Random();
        int int_random = rand.nextInt((sizeX * sizeY) / 25);
        for (int obstacle = 0; obstacle < int_random; obstacle++) {
            int coordonateX_random = rand.nextInt(sizeX);
            int coordonateY_random = rand.nextInt(sizeY);
            while (mapInfo[coordonateX_random][coordonateY_random].getClass() == SafeBox.class || mapInfo[coordonateX_random][coordonateY_random].isBlockedByObstacle()) {
                coordonateX_random = rand.nextInt(sizeX);
                coordonateY_random = rand.nextInt(sizeY);
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

        for (int x = 0; x < sizeX + 2; x++) {
            if (x == 0) {
                System.out.print("┌");
            } else if (x == sizeX + 1) {
                System.out.println("┐");
            } else {
                System.out.print("─");
            }
        }
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
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
                    switch (mapInfo[x][y].getToken().getType()) {
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

                if (x == (sizeX - 1)) {
                    System.out.println("│");
                }
            }
        }
        for (int x = 0; x < sizeX + 2; x++) {
            if (x == 0) {
                System.out.print("└");
            } else if (x == sizeX + 1) {
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

    public List<Directions> availableTiles(int coordinateX, int coordinateY) {
        List<Directions> availableTiles = new ArrayList<>();
        for (int x = coordinateX - 1; x <= coordinateX + 1; x++) {
            for (int y = coordinateY - 1; y <= coordinateY + 1; y++) {
                if (x < sizeX && y < sizeY && x >= 0 && y >= 0) {
                    if (!mapInfo[x][y].isBlockedByObstacle() && !(x == coordinateX && y == coordinateY)) {
                        availableTiles.add(getDirection(coordinateX, coordinateY, x, y));
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

    public void setMaster(int coordinateX, int coordinateY, Master master) {
        ((SafeBox) mapInfo[coordinateX][coordinateY]).setMaster(master);
    }

    public void setOccupied(int coordinateX, int coordinateY, boolean value, Token token) {
        mapInfo[coordinateX][coordinateY].setOccupied(value, token);
    }
    //////////////////////////////////////////////////////////// Path Finder to Safe Zone(inspired by A*)

    private static class Node {
        int coordinateX;
        int coordinateY;
        int gCost;
        int hCost;
        int fCost;

        private Node(int x, int y, int gCost, int hCost, int fCost) {
            coordinateX = x;
            coordinateY = y;
            this.gCost = gCost;
            this.hCost = hCost;
            this.fCost = fCost;
        }
    }

    private int getCost(Node node, Node originNode, Node goalNode) {
        // g cost
        int distanceX = Math.abs(node.coordinateX - originNode.coordinateX);
        int distanceY = Math.abs(node.coordinateY - originNode.coordinateY);
        node.gCost = distanceX + distanceY;

        // h cost
        distanceX = Math.abs(node.coordinateX - goalNode.coordinateX);
        distanceY = Math.abs(node.coordinateY - goalNode.coordinateY);
        node.hCost = distanceX + distanceY;

        // f cost
        node.fCost = node.gCost + node.hCost;
        return node.hCost;
    }

    public Directions safeZonePathFinder(int coordinateX, int coordinateY, Types type) {
        SafeBox goal = null;
        // which safe zone
        switch (type) {
            case FEU:
                goal = (SafeBox) mapInfo[0][sizeY - 1];
                break;
            case TERRE:
                goal = (SafeBox) mapInfo[sizeX - 1][sizeY - 1];
                break;
            case EAU:
                goal = (SafeBox) mapInfo[sizeX - 1][0];
                break;
            case AIR:
                goal = (SafeBox) mapInfo[0][0];
                break;
        }
        Node[][] nodeInfo;
        nodeInfo = new Node[sizeX][sizeY];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                nodeInfo[x][y] = new Node(x, y, 1000, 1000, 1000);
            }
        }
        for (int x = coordinateX - 1; x < coordinateX + 2; x++) {
            for (int y = coordinateY - 1; y < coordinateY + 2; y++) {
                if (!(x == coordinateX && y == coordinateY) && x < sizeX && y < sizeY && x >= 0 && y >= 0) {
                    if (!mapInfo[x][y].isBlockedByObstacle()) {
                        getCost(nodeInfo[x][y], nodeInfo[coordinateX][coordinateY], nodeInfo[goal.getCoordinateX()][goal.getCoordinateY()]);
                    }
                }
            }
        }
        Node bestNode = nodeInfo[coordinateX][coordinateY];
        for (int x = coordinateX - 1; x <= coordinateX + 1; x++) {
            for (int y = coordinateY - 1; y <= coordinateY + 1; y++) {
                if (x < sizeX && y < sizeY && x >= 0 && y >= 0) {
                    if (nodeInfo[x][y].fCost <= bestNode.fCost) {
                        bestNode = nodeInfo[x][y];
                    }
                }

            }
        }
        return getDirection(coordinateX, coordinateY, bestNode.coordinateX, bestNode.coordinateY);
    }
}