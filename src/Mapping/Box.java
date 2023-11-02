package Mapping;
import Tokens.*;

public class Box {
    public int CoordonateX;
    public int CoordonateY;
    private boolean obstacle;
    private boolean occupied;
    protected Token token;

    Box(int x, int y){
        CoordonateX = x;
        CoordonateY = y;
        obstacle = false;
        occupied = false;
        token = null;
    }

    public void setObstacle(){
        obstacle = true;
    }

    public void setOccupied(boolean value, Token token){
        occupied = value;
        this.token = token;
    }

    public boolean isBlockedByObstacle(){
        return obstacle;
    }

    public boolean isOccupied(){
        return occupied;
    }

    public Token getToken(){
        return token;
    }
}