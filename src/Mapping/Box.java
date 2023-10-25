package Mapping;

public class Box {
    public int CoordonateX;
    public int CoordonateY;
    private boolean obstacle;
    private boolean occupied;

    Box(int x, int y){
        CoordonateX = x;
        CoordonateY = y;
        obstacle = false;
    }

    public void setObstacle(){
        obstacle = true;
    }

    public void setOccupied(boolean value){
        occupied = value;
    }

    public boolean isBlockedByObstacle(){
        return obstacle;
    }

    public boolean isOccupied(){
        return occupied;
    }
}