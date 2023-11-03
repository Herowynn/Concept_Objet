package Mapping;
import Tokens.*;

public class Box {
    public int CoordonateX;
    public int CoordonateY;
    private boolean obstacle;
    private boolean occupiedByToken;
    protected boolean occupiedByMaster;
    protected Token token;

    Box(int x, int y){
        CoordonateX = x;
        CoordonateY = y;
        obstacle = false;
        occupiedByToken = false;
        occupiedByMaster = false;
        token = null;
    }

    public void setObstacle(){
        obstacle = true;
    }

    public void setOccupied(boolean value, Token token){
        occupiedByToken = value;
        this.token = token;
    }

    public boolean isBlockedByObstacle(){
        return obstacle;
    }

    public boolean isOccupiedByToken(){
        return occupiedByToken;
    }

    public Token getToken(){
        return token;
    }

    public boolean isOccupiedByMaster(){
        return occupiedByMaster;
    }

    public boolean isSafeZone(){
        return false;
    }
}