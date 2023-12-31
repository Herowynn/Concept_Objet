package Tokens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Elements.*;
import Enums.*;

public class Queen extends Token {

    public Queen(Mapping.Map map, String name, Master master) {
        super(map, name, master);
        letterForMapDisplay = "Q";
        Random random = new Random();
        // All the Queen are supposed to have the same Movement price intervals
        this.maxMovementPrice = 2.0;
        this.minMovementPrice = 0.0;

        // Queen from Hydraterre have less chance to loose energy while their move than
        // Venflamme

        double percentageMovementPriceVenflamme = 0.80;
        double percentageMovementPriceHydraterre = 0.65;

        switch (type) {
            case AIR:
                this.energyMax = 50;
                this.energyLeft = 50;
                this.maxMovementPrice = maxMovementPrice * percentageMovementPriceVenflamme;
                this.minMovementPrice = maxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                this.movementPrice = this.minMovementPrice
                        + (random.nextDouble() * (this.maxMovementPrice - this.minMovementPrice));
                break;

            case TERRE:
                this.energyMax = 40;
                this.energyLeft = 40;
                this.maxMovementPrice = maxMovementPrice * percentageMovementPriceHydraterre;
                this.minMovementPrice = maxMovementPrice * percentageMovementPriceHydraterre;

                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                this.movementPrice = this.minMovementPrice
                        + (random.nextDouble() * (this.maxMovementPrice - this.minMovementPrice));
                break;

            case EAU:
                this.energyMax = 35;
                this.energyLeft = 35;
                this.maxMovementPrice = maxMovementPrice * percentageMovementPriceHydraterre;
                this.minMovementPrice = maxMovementPrice * percentageMovementPriceHydraterre;
                // Generate a random double in this intervale [ 65% of 1.0 and 65% of 3.0] for
                // the Movement price
                this.movementPrice = this.minMovementPrice
                        + (random.nextDouble() * (this.maxMovementPrice - this.minMovementPrice));
                break;

            case FEU:
                this.energyMax = 45;
                this.energyLeft = 45;
                this.maxMovementPrice = maxMovementPrice * percentageMovementPriceVenflamme;
                this.minMovementPrice = maxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 65% of 1.0 and 65% of 3.0] for
                // the Movement price
                this.movementPrice = this.minMovementPrice
                        + (random.nextDouble() * (this.maxMovementPrice - this.minMovementPrice));
                break;

        }
    }

    @Override
    public void moveToFindMessages() {
        gameMap.getMapInfo()[coordinateX][coordinateY].setOccupied(false, this);

        Random random = new Random();

        // List of all the direction the token could choose
        List<Directions> orientation = new ArrayList<>();
        orientation.add(Directions.NE);
        orientation.add(Directions.NW);
        orientation.add(Directions.SE);
        orientation.add(Directions.SW);
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

                case W:
                    coordinateX = coordinateX - 1;
                    break;

                case N:
                    coordinateY = coordinateY + 1;
                    break;

                case S:
                    coordinateY = coordinateY - 1;
                    break;

                case SE:
                    coordinateX = coordinateX + 1;
                    coordinateY = coordinateY - 1;
                    break;

                case SW:
                    coordinateX = coordinateX - 1;
                    coordinateY = coordinateY - 1;
                    break;

                case NE:
                    coordinateX = coordinateX + 1;
                    coordinateY = coordinateY + 1;
                    break;

                case NW:
                    coordinateX = coordinateX - 1;
                    coordinateY = coordinateY + 1;
                    break;

            }
            i++;

        }
        energyLeft = energyLeft - (numberCaseMovement * movementPrice);
        energyLeft = Math.round(energyLeft * 10.0) / 10.0;
        gameMap.getMapInfo()[coordinateX][coordinateY].setOccupied(true, this);

        System.out.println(name + " s'est déplacé en " + coordinateX + ", " + coordinateY);
        System.out.println(name + " s'est déplacé en " + coordinateX + ", " + coordinateY);

        super.moveToFindMessages();
    }
}
