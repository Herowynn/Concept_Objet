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
        this.MaxMovementPrice = 6.0;
        this.MinMovementPrice = 3.0;

        // Bishop from Hydraterre have less chance to loose energy while their move than
        // Venflamme

        double percentageMovementPriceVenflamme = 0.80;
        double percentageMovementPriceHydraterre = 0.65;

        switch (Type) {
            case AIR:
                EnergyMax = 50;
                EnergyLeft = 50;
                MaxMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                MinMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                MovementPrice = MinMovementPrice
                        + (random.nextDouble() * (MaxMovementPrice - MinMovementPrice));
                break;

            case TERRE:
                this.EnergyMax = 40;
                this.EnergyLeft = 40;
                this.MaxMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                this.MinMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;

                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                MovementPrice = MinMovementPrice
                        + (random.nextDouble() * (MaxMovementPrice - MinMovementPrice));
                break;

            case EAU:
                EnergyMax = 35;
                EnergyLeft = 35;
                MaxMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                MinMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                // Generate a random double in this intervale [ 65% of 1.0 and 65% of 3.0] for
                // the Movement price
                MovementPrice = MinMovementPrice
                        + (random.nextDouble() * (MaxMovementPrice - MinMovementPrice));
                break;

            case FEU:
                EnergyMax = 45;
                EnergyLeft = 45;
                MaxMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                MinMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 65% of 1.0 and 65% of 3.0] for
                // the Movement price
                MovementPrice = MinMovementPrice
                        + (random.nextDouble() * (MaxMovementPrice - MinMovementPrice));
                break;

        }
    }

    @Override
    public void MoveToFindMessages() {
        GameMap.getMapInfo()[coordinateX][coordinateY].setOccupied(false, this);

        Random random = new Random();

        // List of all the direction the token could choose
        List<Directions> orientation = new ArrayList<>();
        orientation.add(Directions.S);
        orientation.add(Directions.W);
        orientation.add(Directions.N);
        orientation.add(Directions.E);

        // List of the direction available for the token
        List<Directions> availableDirections = GameMap.availableTiles(coordinateX, coordinateY);

        // Looking for the common direction between those the token could choose and
        // those available
        List<Directions> commonDirections = new ArrayList<>();
        for (Directions element : orientation) {
            if (availableDirections.contains(element)) {
                commonDirections.add(element);
            }
        }

        // Saving of the last direction
        LastDirection = commonDirections.get(random.nextInt(commonDirections.size()));

        // Number of case to move, random umber between 0 and 5 inclusive
        int numberCaseMovement = random.nextInt(4);
        System.out.println("random number" + numberCaseMovement);

        int i = 1;
        while (coordinateX < GameMap.SizeX - 1 && coordinateY < GameMap.SizeY - 1 && coordinateY > 1 && coordinateX > 1 && i <= Math.abs(numberCaseMovement)
                && !GameMap.getMapInfo()[coordinateX][coordinateY].isOccupiedByToken() && !GameMap.getMapInfo()[coordinateX][coordinateY].isBlockedByObstacle()) {
            switch (LastDirection) {
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

        EnergyLeft = EnergyLeft - (numberCaseMovement * MovementPrice);
        EnergyLeft = Math.round(EnergyLeft * 10.0) / 10.0;
        GameMap.getMapInfo()[coordinateX][coordinateY].setOccupied(true, this);

        super.MoveToFindMessages();
    }
}
