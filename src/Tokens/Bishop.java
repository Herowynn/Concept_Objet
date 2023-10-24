package Tokens;

import java.util.Random;

import Enums.Directions;
import Enums.Types;

public class Bishop extends Token {

    public Bishop(Types type, String name) {
        Type = type;
        Name = name;
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
                this.EnergyMax = 50;
                this.MaxMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                this.MinMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                this.MovementPrice = this.MinMovementPrice
                        + (random.nextDouble() * (this.MaxMovementPrice - this.MinMovementPrice));
                break;

            case TERRE:
                this.EnergyMax = 40;
                this.MaxMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                this.MinMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;

                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                this.MovementPrice = this.MinMovementPrice
                        + (random.nextDouble() * (this.MaxMovementPrice - this.MinMovementPrice));
                break;

            case EAU:
                this.EnergyMax = 35;
                this.MaxMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                this.MinMovementPrice = MaxMovementPrice * percentageMovementPriceHydraterre;
                // Generate a random double in this intervale [ 65% of 1.0 and 65% of 3.0] for
                // the Movement price
                this.MovementPrice = this.MinMovementPrice
                        + (random.nextDouble() * (this.MaxMovementPrice - this.MinMovementPrice));
                break;

            case FEU:
                this.EnergyMax = 45;
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
    public void Move() {
        int mapLength = 100; // length of the map
        int mapWidth = 50; // width of the map

        // Number of case to move
        int numberCaseMovement1 = this.generateRandomNumberOfCase();

        // Moving
        int i = 0;
        while (this.CoordinateX < mapLength && i < Math.abs(numberCaseMovement1)) {
            i = i + 1;
            if (numberCaseMovement1 <= 0) {
                this.CoordinateX = this.CoordinateX - i;

            } else {
                this.CoordinateX = this.CoordinateX + i;

            }
            System.out.println(this.CoordinateX);
            this.CoordinateY = 0;

        }
        // Number of case to move
        int numberCaseMovement2 = this.generateRandomNumberOfCase();

        // Moving
        int j = 0;
        while (this.CoordinateX < mapLength && i < Math.abs(numberCaseMovement2)) {
            i = i + 1;
            if (numberCaseMovement2 <= 0) {
                this.CoordinateX = this.CoordinateX - j;

            } else {
                this.CoordinateX = this.CoordinateX + j;

            }
            System.out.println(this.CoordinateX);
            this.CoordinateY = 0;

        }
        // Loss of energy after the moving

        this.EnergyLeft = this.EnergyLeft - ((numberCaseMovement1 + numberCaseMovement2) * this.MovementPrice);
        this.EnergyLeft = Math.round(this.EnergyLeft * 10.0) / 10.0;
        if (this.EnergyLeft < 0) {
            this.EnergyLeft = 0;
        }
        // Saving of the last direction, which is East.
        this.LastDirection = this.calculTheLastDirection(numberCaseMovement1, numberCaseMovement2);

    }

    @Override
    public void EnergyRegeneration() {

    }

    @Override
    public void MessagesExchange() {

    }
}
