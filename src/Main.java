// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import Enums.Types;
import Tokens.Rook;
import Tokens.Queen;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ceci est le code du projet !!!");

        Queen rook1 = new Queen(Types.AIR, "Léonard");
        rook1.getCoordinateXY();
        rook1.Move();
        rook1.getCoordinateXY();
        rook1.getEnergyLeft();
    }
}