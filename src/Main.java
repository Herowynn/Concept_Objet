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

        List<Token> tokens;
        List<Elemental> masters = new ArrayList<>();

        Air AirMaster = Air.getInstance("AirMaster", myMap, 5);
        Fire FireMaster = Fire.getInstance("FireMaster", myMap, 5);
        Water WaterMaster = Water.getInstance("WaterMaster", myMap, 5);
        Earth EarthMaster = Earth.getInstance("EarthMaster", myMap, 5);

        masters.add(AirMaster);
        masters.add(FireMaster);
        masters.add(WaterMaster);
        masters.add(EarthMaster);

        SimulationManager.getInstance(masters);
        tokens = SimulationManager.GetAllTokensFromMasters();
        for (Token token : tokens) {
            System.out.println(token.Name);
        }
    }
}