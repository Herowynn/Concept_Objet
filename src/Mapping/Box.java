package Mapping;

public class Box {
    public int CoordonateX;
    public int CoordonateY;
    public boolean obstacle;

    Box(int x, int y){
        CoordonateX = x;
        CoordonateY = y;
        obstacle = false;
    }

    public void setObstacle(){
        obstacle = true;
    }
}