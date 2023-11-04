package Tokens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Elements.*;
import Enums.*;

public class Queen extends Token {

    public Queen(Mapping.Map map, String name, Elemental master) {
        super(map, name, master);
        letterForMapDisplay = "Q";
        Random random = new Random();
        // All the Queen are supposed to have the same Movement price intervals
        this.MaxMovementPrice = 2.0;
        this.MinMovementPrice = 0.0;

        // Queen from Hydraterre have less chance to loose energy while their move than
        // Venflamme

        double percentageMovementPriceVenflamme = 0.80;
        double percentageMovementPriceHydraterre = 0.65;

        switch (Type) {
            case AIR:
                this.EnergyMax = 50;
                this.EnergyLeft = 50;
                this.MaxMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                this.MinMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                this.MovementPrice = this.MinMovementPrice
                        + (random.nextDouble() * (this.MaxMovementPrice - this.MinMovementPrice));
                break;

            case TERRE:
                this.EnergyMax = 40;
                this.EnergyLeft = 40;
                this.MaxMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                this.MinMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;

                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                this.MovementPrice = this.MinMovementPrice
                        + (random.nextDouble() * (this.MaxMovementPrice - this.MinMovementPrice));
                break;

            case EAU:
                this.EnergyMax = 35;
                this.EnergyLeft = 35;
                this.MaxMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                this.MinMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                // Generate a random double in this intervale [ 65% of 1.0 and 65% of 3.0] for
                // the Movement price
                this.MovementPrice = this.MinMovementPrice
                        + (random.nextDouble() * (this.MaxMovementPrice - this.MinMovementPrice));
                break;

            case FEU:
                this.EnergyMax = 45;
                this.EnergyLeft = 45;
                this.MaxMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                this.MinMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 65% of 1.0 and 65% of 3.0] for
                // the Movement price
                this.MovementPrice = this.MinMovementPrice
                        + (random.nextDouble() * (this.MaxMovementPrice - this.MinMovementPrice));
                break;

        }
    }

    @Override
    public void MoveToFindMessages() {

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
        List<Directions> availableDirections = GameMap.availableTiles(coordinateX, coordinateY);

        // Looking for the common direction between those the token could choose and
        // those available
        List<Directions> commonDirections = new ArrayList<>();
        for (Directions element : orientation) {
            if (availableDirections.contains(element)) {
                commonDirections.add(element);
            }
        }

        // Number of case to move, random umber between 0 and 5 inclusive
        int numberCaseMovement = random.nextInt(6);
        System.out.println("random number" + numberCaseMovement);
        int i = 1;
        while (coordinateX < GameMap.SizeX && coordinateY < GameMap.SizeY && i <= Math.abs(numberCaseMovement)
                && !GameMap.getMapInfo()[coordinateX][coordinateY].isOccupiedByToken()) {
            switch (LastDirection) {
                case E:
                    coordinateX = coordinateX + i;
                    break;

                case W:
                    coordinateX = coordinateX - i;
                    break;

                case N:
                    coordinateY = coordinateY + i;
                    break;

                case S:
                    coordinateY = coordinateY - i;
                    break;

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
