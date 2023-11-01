package Tokens;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Elements.*;
import Enums.*;
import Mapping.SafeBox;

public abstract class Token {
    public Types Type;
    public String[] KnownMessages;
    public String Name;
    public double EnergyLeft;
    public double EnergyMax;
    public double MovementPrice;
    public double MinMovementPrice;
    public double MaxMovementPrice;
    public int CoordinateX;
    public int CoordinateY;
    public Directions LastDirection;
    protected Mapping.Map GameMap;
    protected Elemental master;

    public void EnergyRegeneration(){

    }

    public Token(Mapping.Map map, Elemental master){
        GameMap = map;
        this.master = master;
    }

    public void Move(){
        verifyBoxes();
    }

    protected void verifyBoxes(){
        if(GameMap.MapInfos[CoordinateX][CoordinateY] instanceof SafeBox && Type == GameMap.MapInfos[CoordinateX][CoordinateY].type){
            EnergyRegeneration();
        }
        else if(Type == GameMap.MapInfos[CoordinateX + 1][CoordinateY + 1].type || Type == GameMap.MapInfos[CoordinateX + 1][CoordinateY].type
        || Type == GameMap.MapInfos[CoordinateX][CoordinateY + 1].type ||  Type == GameMap.MapInfos[CoordinateX - 1][CoordinateY - 1].type || Type == GameMap.MapInfos[CoordinateX - 1][CoordinateY].type
        || Type == GameMap.MapInfos[CoordinateX][CoordinateY - 1].type || Type == GameMap.MapInfos[CoordinateX + 1][CoordinateY - 1].type
        || Type == GameMap.MapInfos[CoordinateX - 1][CoordinateY + 1].type){
            MessagesExchangeBetweenSameTypes();
        }
        else if(GameMap.MapInfos[CoordinateX + 1][CoordinateY + 1].isOccupied() || GameMap.MapInfos[CoordinateX + 1][CoordinateY].isOccupied()
                || GameMap.MapInfos[CoordinateX][CoordinateY + 1].isOccupied() ||  GameMap.MapInfos[CoordinateX - 1][CoordinateY - 1].isOccupied()
                || GameMap.MapInfos[CoordinateX - 1][CoordinateY].isOccupied() || GameMap.MapInfos[CoordinateX][CoordinateY - 1].isOccupied()
                || GameMap.MapInfos[CoordinateX + 1][CoordinateY - 1].isOccupied() || GameMap.MapInfos[CoordinateX - 1][CoordinateY + 1].isOccupied()){
            MessagesExchangeBetweenEnemies();
        }
        else if(!GameMap.MapInfos[CoordinateX + 1][CoordinateY + 1].isOccupied() || !GameMap.MapInfos[CoordinateX + 1][CoordinateY].isOccupied()
                || !GameMap.MapInfos[CoordinateX][CoordinateY + 1].isOccupied() ||  !GameMap.MapInfos[CoordinateX - 1][CoordinateY - 1].isOccupied()
                || !GameMap.MapInfos[CoordinateX - 1][CoordinateY].isOccupied() || !GameMap.MapInfos[CoordinateX][CoordinateY - 1].isOccupied()
                || !GameMap.MapInfos[CoordinateX + 1][CoordinateY - 1].isOccupied() || !GameMap.MapInfos[CoordinateX - 1][CoordinateY + 1].isOccupied()){
            return;
        }
        else{
            MessagesExchangeBetweenAllies();
        }
    }

    public Map<String, Integer> getCoordinateXY() {
        Map<String, Integer> coordinatesXY = new HashMap<>();
        // Ajoute des valeurs pour les coordonnées X et Y
        coordinatesXY.put("coordinateX", CoordinateX);
        coordinatesXY.put("coordinateY", CoordinateY);
        System.out.println(coordinatesXY);
        return coordinatesXY;
    }

    public double getEnergyLeft() {
        System.out.println("The energy of the token is: " + this.EnergyLeft);
        return EnergyLeft;
    }

    // The choice of the direction will be done randomly at each move
    public void ChoixDirection() {
        // We put all the direction in a list
        Directions[] directions = Directions.values();

        Random random = new Random();

        // Generation of a random number to choose the direction in the list
        int indice = random.nextInt(directions.length);

        // Selection of the direction corresponding to the random number
        Directions randomDirection = directions[indice];

        // Print the chosen direction
        System.out.println("The token will take the direction " + randomDirection);
    }

    // Method for moving from a random number of cases.
    public int generateRandomNumberOfCase() {
        Random random = new Random();
        int min = -5;
        int max = 5;

        // Generate a random number between -5 and 5, inclusive
        int randomNumber = random.nextInt((max - min) + 1) + min;
        System.out.println("The random number is: " + randomNumber);
        return randomNumber;
    }

    // This method calcul the last direction of the token according to the
    // coordinates of moving
    // coordinatesX et coordinatesY are the coordinates the token will take at his future move
    public Directions calculTheLastDirection(int coordinateX, int coordinateY) {
        Directions lastDirection;
        if (coordinateX > 0 && coordinateY > 0) {
            lastDirection = Directions.NE;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX > 0 && coordinateY < 0) {
            lastDirection = Directions.SE;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX < 0 && coordinateY < 0) {
            lastDirection = Directions.SW;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX < 0 && coordinateY > 0) {
            lastDirection = Directions.NW;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX == 0 && coordinateY > 0) {
            lastDirection = Directions.N;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX == 0 && coordinateY < 0) {
            lastDirection = Directions.S;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX < 0 && coordinateY == 0) {
            lastDirection = Directions.W;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX > 0 && coordinateY == 0) {
            lastDirection = Directions.E;
            System.out.println("The last direction was " + lastDirection);
        } else {
            lastDirection = null;
            System.out.println("The last direction was " + lastDirection);
        }

        return lastDirection;
    }

    public void MessagesExchangeBetweenAllies(){

    }
    public void MessagesExchangeBetweenSameTypes(){

    }
    public void MessagesExchangeBetweenEnemies(){

    }

}
