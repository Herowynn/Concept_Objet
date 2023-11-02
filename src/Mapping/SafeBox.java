package Mapping;

import Elements.*;
import Enums.*;

public class SafeBox extends Box{
    public Types Type;
    private Elemental master;

    public SafeBox(int x, int y, Types type) {
        super(x, y);
        Type = type;
    }
    public Types getType(){
        return Type;
    }

    public void setMaster(Elemental master){
        occupiedByMaster = true;
        this.master = master;
    }

    public Elemental getMaster(){
        return master;
    }
}