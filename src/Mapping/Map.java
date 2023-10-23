package Mapping;

import java.util.Random;

import Enums.Types;

public class Map {
    public int SizeX;
    public int SizeY;
    public Box[][] MapInfos;

    public Map(int sizeX, int sizeY){
        SizeX = sizeX;
        SizeY = sizeY;
        MapInfos = new Box[SizeX][SizeY];
        for(int x = 0; x < SizeX; x++){
            for(int y = 0; y < SizeY; y++){
                MapInfos[x][y] = initiateBox(x, y);
            }
        }
        obstacleGeneration();
    }

    private Box initiateBox(int x, int y){
        if(x < safeZoneSize()[0] && y < safeZoneSize()[1]){
            return new SafeBox(x, y, Types.AIR);
        }
        else if(x > (SizeX - safeZoneSize()[0] - 1) && y < safeZoneSize()[1]){
            return new SafeBox(x, y, Types.EAU);
        }
        else if(x < safeZoneSize()[0] && y > (SizeY - safeZoneSize()[1] - 1)){
            return new SafeBox(x, y, Types.FEU);
        }
        else if(x > (SizeX - safeZoneSize()[0] - 1) && y > (SizeY - safeZoneSize()[1] - 1)){
            return new SafeBox(x, y, Types.TERRE);
        }
        return new Box(x, y);
    }

    private int[] safeZoneSize(){
        int safeZoneSizeX = SizeX / 4;
        int safeZoneSizeY = SizeY / 4;

        int[] safeZoneSize = {safeZoneSizeX, safeZoneSizeY};
        return safeZoneSize;
    }

    private void obstacleGeneration(){
        Random r = new Random();
        int int_random = r.nextInt((SizeX * SizeY) / 25); 
        for(int obstacle = 0; obstacle < int_random; obstacle++){
            int coordonateX_random = r.nextInt(SizeX);
            int coordonateY_random = r.nextInt(SizeY);
            while(MapInfos[coordonateX_random][coordonateY_random].getClass() == SafeBox.class || MapInfos[coordonateX_random][coordonateY_random].obstacle){
                coordonateX_random = r.nextInt(SizeX);
                coordonateY_random = r.nextInt(SizeY);
            }
            MapInfos[coordonateX_random][coordonateY_random].setObstacle();
        }
    }

    public void printMap(){
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m"; 
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m"; 
        final String ANSI_BLUE_BACKGROUND = "\u001B[44m"; 
        final String ANSI_PURPLE_BACKGROUND = "\u001B[45m"; 
        for(int x = 0; x < SizeX + 2; x++){
            if(x == 0){
                System.out.print("┌");
            }
            else if(x == SizeX + 1){
                System.out.println("┐");
            }
            else{
                System.out.print("─");
            }
        }
        for(int y = 0; y < SizeY; y++){
            for(int x = 0; x < SizeX; x++){
                if(x == 0){
                    System.out.print("│");
                }
                if(MapInfos[x][y].getClass() == SafeBox.class){
                    switch(((SafeBox) MapInfos[x][y]).getType()){
                        case FEU:
                            System.out.print(ANSI_RED_BACKGROUND + " " + ANSI_RESET);   
                            break;
                        case EAU:
                            System.out.print(ANSI_BLUE_BACKGROUND + " " + ANSI_RESET);   
                            break;
                        case AIR:
                            System.out.print(ANSI_PURPLE_BACKGROUND + " " + ANSI_RESET);   
                            break;
                        case TERRE:
                            System.out.print(ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);   
                            break;
                    }
                }
                else if(MapInfos[x][y].obstacle == true){
                    System.out.print("/");   

                }
                else{
                    System.out.print(" ");   
                }

                if(x == (SizeX - 1)){
                    System.out.println("│");
                }
            }
        }
        for(int x = 0; x < SizeX + 2; x++){
            if(x == 0){
                System.out.print("└");
            }
            else if(x == SizeX + 1){
                System.out.println("┘");
            }
            else{
                System.out.print("─");
            }
        }
    }

    public Box[] availableTiles(int coordonateX, int coordonateY){
        Box[] availableTiles = new Box[8];
        for(int x = coordonateX - 1; x <= coordonateX + 1; x++){
            for(int y = coordonateY - 1; y <= coordonateY + 1; y++){
                if(x != -1 && y != -1 && x != SizeX && y != SizeY){
                    if(!MapInfos[x][y].obstacle){
                        availableTiles[availableTiles.length] = MapInfos[x][y];
                    }
                }
            }
        }
        return availableTiles;
    }
}