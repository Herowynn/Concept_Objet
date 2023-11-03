package Tokens;

import java.util.Random;

import Enums.*;
import Elements.*;

public class Bishop extends Token {
    public Bishop(Mapping.Map map, String name, Elemental master) {
        super(map, name, master);
        Random random = new Random();
        // All the Bishop are suppose to have the same Movement price intervals
        this.MaxMovementPrice = 3.0;
        this.MinMovementPrice = 1.0;

        // Bishop from Hydraterre have less chance to loose energy while their move than
        // Venflamme

        double percentageMovementPriceVenflamme = 0.80;
        double percentageMovementPriceHydraterre = 0.65;

        switch (Type) {
            case AIR:
                EnergyMax = 50;
                MaxMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                MinMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                MovementPrice = MinMovementPrice
                        + (random.nextDouble() * (MaxMovementPrice - MinMovementPrice));
                break;

            case TERRE:
                EnergyMax = 40;
                MaxMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                MinMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;

                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                MovementPrice = MinMovementPrice
                        + (random.nextDouble() * (MaxMovementPrice - MinMovementPrice));
                break;

            case EAU:
                EnergyMax = 35;
                MaxMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                MinMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                // Generate a random double in this intervale [ 65% of 1.0 and 65% of 3.0] for
                // the Movement price
                MovementPrice = MinMovementPrice
                        + (random.nextDouble() * (MaxMovementPrice - MinMovementPrice));
                break;

            case FEU:
                EnergyMax = 45;
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

        Random random = new Random();

        // List of all the direction the token could choose
        Directions[] orientation = { Directions.NE, Directions.NW,
                Directions.SE, Directions.SW };

        // Saving of the llast direction
        LastDirection = orientation[random.nextInt(orientation.length)];

        // Number of case to move, random umber between 0 and 5 inclusive
        int numberCaseMovement = random.nextInt(6);
        System.out.println("random number" + numberCaseMovement);
        int i = 1;
        while (coordinateX < GameMap.SizeX && coordinateY < GameMap.SizeY && i <= Math.abs(numberCaseMovement)
                && !GameMap.getMapInfo()[coordinateX][coordinateY].isOccupiedByToken()) {
            switch (LastDirection) {

                case SE:
                    coordinateX = coordinateX + i;
                    coordinateY = coordinateY - i;
                    break;

                case SW:
                    coordinateX = coordinateX - i;
                    coordinateY = coordinateY - i;
                    break;

                case NE:
                    coordinateX = coordinateX + i;
                    coordinateY = coordinateY + i;
                    break;

                case NW:
                    coordinateX = coordinateX - i;
                    coordinateY = coordinateY + i;
                    break;

            }
            i++;

        }
        EnergyLeft = EnergyLeft - (numberCaseMovement * MovementPrice);
        EnergyLeft = Math.round(EnergyLeft * 10.0) / 10.0;

        super.MoveToFindMessages();
    }
}
