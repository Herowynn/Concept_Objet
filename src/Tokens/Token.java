package Tokens;

import Enums.*;

public abstract class Token {
    public Types Type;
    public String[] KnownMessages;
    public String name;
    public int EnergyLeft;
    public int EnergyMax;
    public int MovementPrice;
    public int MinMovementPrice;
    public int MaxMovementPrice;
    public int CoordinateX;
    public int CoordinateY;
    public Directions LastDirection;


    abstract public void Move();
    abstract public void EnergyRegeneration();
    abstract public void MessagesExchange();


}
