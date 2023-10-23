package Tokens;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Enums.*;

public abstract class Token {
    public Types Type;
    public String[] KnownMessages;
    public String name;
    public double EnergyLeft;
    public double EnergyMax;
    public double MovementPrice;
    public double MinMovementPrice;
    public double MaxMovementPrice;
    public int CoordinateX;
    public int CoordinateY;
    public Directions LastDirection;

    abstract public void Move();

    abstract public void EnergyRegeneration();

    abstract public void MessagesExchange();

    public Map<String, Integer> getCoordinateXY() {
        Map<String, Integer> coordinatesXY = new HashMap<>();
        // Ajoute des valeurs pour les coordonnées X et Y
        coordinatesXY.put("coordinateX", this.CoordinateX);
        coordinatesXY.put("coordinateY", this.CoordinateY);
        System.out.println(coordinatesXY);
        return coordinatesXY;

    }

    public double getEnergyLeft() {
        System.out.println("The energy of the token is: " + this.EnergyLeft);
        return this.EnergyLeft;
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

        // Generate a random number between 0 and 5, inclusive
        int randomNumber = random.nextInt((max - min) + 1) + min;
        System.out.println("The random number is: " + randomNumber);
        return randomNumber;

    }

    // This method calcul the last direction of the token according to the
    // coordinates of moving
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

}
