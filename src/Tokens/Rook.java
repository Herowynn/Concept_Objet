package Tokens;

import java.util.Random;

import Enums.Directions;
import Enums.Types;

public class Rook extends Token {

    public Rook(Types Type, String name) {
        this.Type = Type;
        this.name = name;
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
                this.EnergyMax = 50;
                this.EnergyLeft = 50;
                this.MaxMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                this.MinMovementPrice = MaxMovementPrice * percentageMovementPriceVenflamme;
                // Generate a random double in this intervale [ 80% of 1.0 and 80% of 3.0] for
                // the Movement price
                this.MovementPrice = this.MinMovementPrice
                        + (random.nextDouble() * (this.MaxMovementPrice - this.MinMovementPrice));

                System.out.println(this.name + " is a rook from the Air group, their team is Venflamme.");
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

                System.out.println(this.name + " is a rook from the Earth group, their team is Hydraterre.");
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
                System.out.println(this.name + " is a rook from the Water group, their team is Hydraterre.");
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
                System.out.println(this.name + " is a rook from the Fire group, their team is Venflamme.");
                break;

        }
    }

    @Override
    public void Move() {

        if (this.EnergyLeft >= 0.20 * EnergyMax) {
            // The rooks move only two squares to the left.

            int mapLength = 100; // length of the map
            int mapWidth = 50; // width of the map

            // Number of case to move
            int numberCaseMovement = this.generateRandomNumberOfCase();

            // Moving

            // obstacles
            int i = 0;
            while (this.CoordinateX < mapLength && i < Math.abs(numberCaseMovement)) {
                i = i + 1;
                if (numberCaseMovement <= 0) {
                    this.CoordinateX = this.CoordinateX - i;

                } else {
                    this.CoordinateX = this.CoordinateX + i;

                }
                System.out.println(this.CoordinateX);
                this.CoordinateY = 0;

            }
            // Loss of energy after the moving
            this.EnergyLeft = this.EnergyLeft - (numberCaseMovement * this.MovementPrice);
            this.EnergyLeft = Math.round(this.EnergyLeft * 10.0) / 10.0;
            if (this.EnergyLeft < 0) {
                this.EnergyLeft = 0;
            }
            // Saving of the last direction
            this.LastDirection = this.calculTheLastDirection(numberCaseMovement, 0);

        }
    }

    @Override
    public void EnergyRegeneration() {

    }

    @Override
    public void MessagesExchange() {

    }
}
