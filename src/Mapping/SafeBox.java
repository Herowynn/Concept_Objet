package Mapping;

import Enums.Types;

public class SafeBox extends Box{
    public Types Type;

    SafeBox(int x, int y, Types type) {
        super(x, y);
        Type = type;
    }
    public Types getType(){
        return Type;
    }
}