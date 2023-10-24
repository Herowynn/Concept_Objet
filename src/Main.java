// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import Elements.*;
import Mapping.Map;


import Enums.Types;
import Tokens.Rook;
import Tokens.Queen;

public class Main {
    public static void main(String[] args) {
        /*System.out.println("Ceci est le code du projet !!!");
        Map test = new Map(51, 14);
        test.printMap();


        Queen rook1 = new Queen(Types.AIR, "Léonard");
        rook1.getCoordinateXY();
        rook1.Move();
        rook1.getCoordinateXY();
        rook1.getEnergyLeft();*/

        Air AirMaster = Air.getInstance("AirMaster");
        Fire FireMaster = Fire.getInstance("FireMaster");
        Water WaterMaster = Water.getInstance("WaterMaster");
        Earth EarthMaster = Earth.getInstance("EarthMaster");
    }
}