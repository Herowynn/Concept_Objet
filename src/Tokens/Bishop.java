package Tokens;

import java.util.Random;

import Enums.*;
import Elements.*;

public class Bishop extends Token {

    public Bishop(Mapping.Map map, Types type, String name, Elemental master) {
        super(map, master);
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

    public void Move() {
        // Number of case to move
        int numberCaseMovement1 = this.generateRandomNumberOfCase();

        // Moving
        int i = 0;
        while (this.CoordinateX < GameMap.SizeX && i < Math.abs(numberCaseMovement1)) {
            i = i + 1;
            if (numberCaseMovement1 <= 0) {
                CoordinateX = CoordinateX - i;

            } else {
                CoordinateX = CoordinateX + i;

            }
            System.out.println(CoordinateX);
            CoordinateY = 0;

        }
        // Number of case to move
        int numberCaseMovement2 = generateRandomNumberOfCase();

        // Moving
        int j = 0;
        while (CoordinateX < GameMap.SizeX && i < Math.abs(numberCaseMovement2)) {
            i = i + 1;
            if (numberCaseMovement2 <= 0) {
                CoordinateX = CoordinateX - j;

            } else {
                CoordinateX = CoordinateX + j;

            }
            System.out.println(CoordinateX);
            CoordinateY = 0;
        }
        // Loss of energy after the moving

        EnergyLeft = EnergyLeft - ((numberCaseMovement1 + numberCaseMovement2) * MovementPrice);
        EnergyLeft = Math.round(EnergyLeft * 10.0) / 10.0;
        if (EnergyLeft < 0) {
            EnergyLeft = 0;
        }
        // Saving of the last direction, which is East.
        LastDirection = calculTheLastDirection(numberCaseMovement1, numberCaseMovement2);
    }
}
