package Tokens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Elements.*;
import Enums.*;

public class Rook extends Token {

    public Rook(Mapping.Map map, String name, Master master) {
        super(map, name, master);
        letterForMapDisplay = "R";
        Random random = new Random();
        // All the Bishop are suppose to have the same Movement price intervals
        this.maxMovementPrice = 6.0;
        this.minMovementPrice = 3.0;

        // Bishop from Hydraterre have less chance to loose energy while their move than
        // Venflamme

        double percentageMovementPriceVenflamme = 0.80;
        double percentageMovementPriceHydraterre = 0.65;

        switch (type) {
            case AIR:
                energyMax = 50;
                energyLeft = 50;
                maxMovementPrice = maxMovementPrice * percentageMovementPriceVenflamme;
                minMovementPrice = maxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                movementPrice = minMovementPrice
                        + (random.nextDouble() * (maxMovementPrice - minMovementPrice));
                break;

            case TERRE:
                this.energyMax = 40;
                this.energyLeft = 40;
                this.maxMovementPrice = maxMovementPrice * percentageMovementPriceHydraterre;
                this.minMovementPrice = maxMovementPrice * percentageMovementPriceHydraterre;

                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                movementPrice = minMovementPrice
                        + (random.nextDouble() * (maxMovementPrice - minMovementPrice));
                break;

            case EAU:
                energyMax = 35;
                energyLeft = 35;
                maxMovementPrice = maxMovementPrice * percentageMovementPriceHydraterre;
                minMovementPrice = maxMovementPrice * percentageMovementPriceHydraterre;
                // Generate a random double in this intervale [ 65% of 1.0 and 65% of 3.0] for
                // the Movement price
                movementPrice = minMovementPrice
                        + (random.nextDouble() * (maxMovementPrice - minMovementPrice));
                break;

            case FEU:
                energyMax = 45;
                energyLeft = 45;
                maxMovementPrice = maxMovementPrice * percentageMovementPriceVenflamme;
                minMovementPrice = maxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 65% of 1.0 and 65% of 3.0] for
                // the Movement price
                movementPrice = minMovementPrice
                        + (random.nextDouble() * (maxMovementPrice - minMovementPrice));
                break;

        }
    }

    @Override
    public void moveToFindMessages() {
        gameMap.getMapInfo()[coordinateX][coordinateY].setOccupied(false, this);

        Random random = new Random();

        // List of all the direction the token could choose
        List<Directions> orientation = new ArrayList<>();
        orientation.add(Directions.S);
        orientation.add(Directions.W);
        orientation.add(Directions.N);
        orientation.add(Directions.E);

        // List of the direction available for the token
        List<Directions> availableDirections = gameMap.availableTiles(coordinateX, coordinateY);

        // Looking for the common direction between those the token could choose and
        // those available
        List<Directions> commonDirections = new ArrayList<>();
        for (Directions element : orientation) {
            if (availableDirections.contains(element)) {
                commonDirections.add(element);
            }
        }

        // Saving of the last direction
        lastDirection = commonDirections.get(random.nextInt(commonDirections.size()));

        // Number of case to move, random umber between 0 and 5 inclusive
        int numberCaseMovement = random.nextInt(4);
        System.out.println("random number" + numberCaseMovement);

        int i = 1;
        while (coordinateX < gameMap.sizeX - 1 && coordinateY < gameMap.sizeY - 1 && coordinateY > 1 && coordinateX > 1 && i <= Math.abs(numberCaseMovement)
                && !gameMap.getMapInfo()[coordinateX][coordinateY].isOccupiedByToken() && !gameMap.getMapInfo()[coordinateX][coordinateY].isBlockedByObstacle()) {
            switch (lastDirection) {
                case E:
                    coordinateX = coordinateX + 1;
                    break;

                case N:
                    coordinateY = coordinateY + 1;
                    break;

                case S:
                    coordinateY = coordinateY - 1;
                    break;

                case W:
                    coordinateX = coordinateX - 1;
                    break;

            }
            i++;
        }

        energyLeft = energyLeft - (numberCaseMovement * movementPrice);
        energyLeft = Math.round(energyLeft * 10.0) / 10.0;
        gameMap.getMapInfo()[coordinateX][coordinateY].setOccupied(true, this);

        super.moveToFindMessages();
    }
}
