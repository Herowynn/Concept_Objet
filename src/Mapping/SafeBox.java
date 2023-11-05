package Mapping;

import Elements.*;
import Enums.*;

public class SafeBox extends Box{
    private final Types type;
    private Master master;

    public SafeBox(int x, int y, Types type) {
        super(x, y);
        this.type = type;
    }
    public Types getType(){
        return type;
    }

    public void setMaster(Master master){
        occupiedByMaster = true;
        this.master = master;
    }

    @Override
    public boolean isSafeZone() {
        return true;
    }

    public Master getMaster(){
        return master;
    }
}