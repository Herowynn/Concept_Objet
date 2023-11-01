package Tokens;

import java.util.Random;

import Enums.*;
import Elements.*;

public class Bishop extends Token {
    public Bishop(Mapping.Map map, String name, Elemental master) {
        super(map, master);
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
    public void Move() {
        // Number of case to move
        int numberCaseMovement1 = generateRandomNumberOfCase();

        // Moving
        int i = 0;
        while (CoordinateX < GameMap.SizeX && i < Math.abs(numberCaseMovement1)) {
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
        super.Move();
    }
}
