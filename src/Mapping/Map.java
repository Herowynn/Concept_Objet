package Mapping;

import java.util.HashSet;
import java.util.Random;
import java.util.Dictionary;
import java.util.Hashtable;

import Elements.Elemental;
import Enums.Directions;
import Enums.Types;

public class Map {
    public int SizeX;
    public int SizeY;
    public Box[][] MapInfos;

    public Map(int sizeX, int sizeY) {
        SizeX = sizeX;
        SizeY = sizeY;
        MapInfos = new Box[SizeX][SizeY];
        for (int x = 0; x < SizeX; x++) {
            for (int y = 0; y < SizeY; y++) {
                MapInfos[x][y] = initiateBox(x, y);
            }
        }
        obstacleGeneration();
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
            while (MapInfos[coordonateX_random][coordonateY_random].getClass() == SafeBox.class || MapInfos[coordonateX_random][coordonateY_random].isBlockedByObstacle()) {
                coordonateX_random = r.nextInt(SizeX);
                coordonateY_random = r.nextInt(SizeY);
            }
            MapInfos[coordonateX_random][coordonateY_random].setObstacle();
        }
    }

    public void printMap() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
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
                if (MapInfos[x][y].getClass() == SafeBox.class) {
                    switch (((SafeBox) MapInfos[x][y]).getType()) {
                        case FEU:
                            if(((SafeBox)MapInfos[x][y]).isOcccupiedByMaster())
                                System.out.print(ANSI_RED_BACKGROUND + "M" + ANSI_RESET);
                            else
                                System.out.print(ANSI_RED_BACKGROUND + " " + ANSI_RESET);
                            break;
                        case EAU:
                            if(((SafeBox)MapInfos[x][y]).isOcccupiedByMaster())
                                System.out.print(ANSI_BLUE_BACKGROUND + "M" + ANSI_RESET);
                            else
                                System.out.print(ANSI_BLUE_BACKGROUND + " " + ANSI_RESET);
                            break;
                        case AIR:
                            if(((SafeBox)MapInfos[x][y]).isOcccupiedByMaster())
                                System.out.print(ANSI_PURPLE_BACKGROUND + "M" + ANSI_RESET);
                            else
                                System.out.print(ANSI_PURPLE_BACKGROUND + " " + ANSI_RESET);
                            break;
                        case TERRE:
                            if(((SafeBox)MapInfos[x][y]).isOcccupiedByMaster())
                                System.out.print(ANSI_GREEN_BACKGROUND + "M" + ANSI_RESET);
                            else
                                System.out.print(ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);
                            break;
                    }
                } else if (MapInfos[x][y].isBlockedByObstacle()) {
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
                return Directions.N;
            } else {
                return Directions.NE;
            }
        } else if (baseX == newX) {
            if (baseY > newY) {
                return Directions.W;
            } else if (baseY < newY) {
                return Directions.E;
            }
        } else if (baseX < newX) {
            if (baseY > newY) {
                return Directions.SW;
            } else if (baseY == newY) {
                return Directions.S;
            } else {
                return Directions.SE;
            }
        }
        return null;
    }

    public Dictionary<Box, Directions> availableTiles(int coordonateX, int coordonateY) {
        Dictionary<Box, Directions> availableTiles = new Hashtable<>();
        for (int x = coordonateX - 1; x <= coordonateX + 1; x++) {
            for (int y = coordonateY - 1; y <= coordonateY + 1; y++) {
                if (x != -1 && y != -1 && x != SizeX && y != SizeY) {
                    if (!MapInfos[x][y].isBlockedByObstacle() && (x != coordonateX && y != coordonateY)) {
                        availableTiles.put(MapInfos[x][y], getDirection(coordonateX, coordonateY, x, y));
                    }
                }
            }
        }
        return availableTiles;
    }

    public HashSet<String> getBoxesFromMySafeZone(Types type) {
        HashSet<String> safeZone = new HashSet<>();
        String values;

        for (int i = 0; i < MapInfos.length; i++) {
            for (int j = 0; j < MapInfos[0].length; j++){
                if(MapInfos[i][j] instanceof SafeBox && ((SafeBox) MapInfos[i][j]).getType() == type){
                    values = i + "," + j;
                    safeZone.add(values);
                }
            }
        }

        return safeZone;
    }

    public void setMaster(int coordinateX, int coordinateY, Elemental master) {
        ((SafeBox) MapInfos[coordinateX][coordinateY]).setMaster(master);
    }
}