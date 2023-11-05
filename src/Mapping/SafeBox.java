package Mapping;

import Elements.*;
import Enums.*;

public class SafeBox extends Box{
    public Types Type;
    private Master master;

    public SafeBox(int x, int y, Types type) {
        super(x, y);
        Type = type;
    }
    public Types getType(){
        return Type;
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