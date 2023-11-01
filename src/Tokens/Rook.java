package Tokens;

import java.util.Random;

import Elements.*;
import Enums.*;

public class Rook extends Token {

    public Rook(Mapping.Map map, Types Type, String name, Elemental master) {
        super(map, master);
        this.Type = Type;
        this.Name = name;
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

                System.out.println(this.Name + " is a rook from the Air group, their team is Venflamme.");
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

                System.out.println(name + " is a rook from the Earth group, their team is Hydraterre.");
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
                System.out.println(name + " is a rook from the Water group, their team is Hydraterre.");
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
                System.out.println(name + " is a rook from the Fire group, their team is Venflamme.");
                break;

        }
    }

    @Override
    public void Move() {

        if (EnergyLeft >= 0.20 * EnergyMax) {
            // The rooks move only two squares to the left.

            int mapLength = 100; // length of the map
            int mapWidth = 50; // width of the map

            // Number of case to move
            int numberCaseMovement = generateRandomNumberOfCase();

            // Moving

            // obstacles
            int i = 0;
            while (CoordinateX < mapLength && i < Math.abs(numberCaseMovement)) {
                i = i + 1;
                if (numberCaseMovement <= 0) {
                    CoordinateX = CoordinateX - i;

                } else {
                    CoordinateX = CoordinateX + i;

                }
                System.out.println(CoordinateX);
                CoordinateY = 0;

            }
            // Loss of energy after the moving
            EnergyLeft = EnergyLeft - (numberCaseMovement * MovementPrice);
            EnergyLeft = Math.round(EnergyLeft * 10.0) / 10.0;
            if (EnergyLeft < 0) {
                EnergyLeft = 0;
            }
            // Saving of the last direction
            LastDirection = calculTheLastDirection(numberCaseMovement, 0);

        }
    }
}
