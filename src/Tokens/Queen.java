package Tokens;

import java.util.Random;

import Enums.Directions;
import Enums.Types;

public class Queen extends Token {

    public Queen(Types Type, String name) {
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
    public void Move() {

        int mapLength = 100; // length of the map
        int mapWidth = 50; // width of the map
        Random random = new Random();
        int[] orientations = { -1, 1 };

        // Utilisez l'indice pour sélectionner le chiffre correspondant dans le tableau

        // Number of case to move, random umber between 0 and 5 inclusive
        int numberCaseMovement = random.nextInt(6);
        System.out.println("random number" + numberCaseMovement);

        // diagonal moves for the queen

        int i = 1;
        int orientation1 = orientations[random.nextInt(2)];
        int orientation2 = orientations[random.nextInt(2)];
        while (this.CoordinateX < mapLength && this.CoordinateY < mapWidth && i <= Math.abs(numberCaseMovement)) {

            this.CoordinateX = this.CoordinateX + orientation1 * i;

            this.CoordinateY = this.CoordinateY + orientation2 * i;
            System.out.println(this.CoordinateX);
            System.out.println(this.CoordinateY);
            i = i + 1;
        }

        this.EnergyLeft = this.EnergyLeft - (numberCaseMovement * this.MovementPrice);
        this.EnergyLeft = Math.round(this.EnergyLeft * 10.0) / 10.0;
        // Saving of the last direction
        this.LastDirection = this.calculTheLastDirection(orientation1 * numberCaseMovement,
                orientation2 * numberCaseMovement);

    }

    @Override
    public void EnergyRegeneration() {

    }

    @Override
    public void MessagesExchange() {

    }
}
