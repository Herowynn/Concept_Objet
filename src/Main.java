// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import Elements.*;
import Mapping.*;


import Enums.Types;
import Tokens.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Map myMap = new Map(51, 14);
        myMap.printMap();

        /*Queen rook1 = new Queen(Types.AIR, "Léonard");
        rook1.getCoordinateXY();
        rook1.Move();
        rook1.getCoordinateXY();
        rook1.getEnergyLeft();*/

        List<Token> tokens = new ArrayList<Token>();
        Air AirMaster = Air.getInstance("AirMaster", myMap);
        Fire FireMaster = Fire.getInstance("FireMaster", myMap);
        Water WaterMaster = Water.getInstance("WaterMaster", myMap);
        Earth EarthMaster = Earth.getInstance("EarthMaster", myMap);
    }
}