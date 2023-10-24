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
        /*System.out.println("Ceci est le code du projet !!!");
        Map test = new Map(51, 14);
        test.printMap();


        Queen rook1 = new Queen(Types.AIR, "Léonard");
        rook1.getCoordinateXY();
        rook1.Move();
        rook1.getCoordinateXY();
        rook1.getEnergyLeft();*/

        List<Token> tokens = new ArrayList<Token>();

        Air AirMaster = Air.getInstance("AirMaster");
        Fire FireMaster = Fire.getInstance("FireMaster");
        Water WaterMaster = Water.getInstance("WaterMaster");
        Earth EarthMaster = Earth.getInstance("EarthMaster");
        System.out.println("Air");
        for(Token token : AirMaster.GetTokenList()){
            System.out.println(token.Name);
        }
        System.out.println("Fire");
        for(Token token : FireMaster.GetTokenList()){
            System.out.println(token.Name);
        }
        System.out.println("Earth");
        for(Token token : EarthMaster.GetTokenList()){
            System.out.println(token.Name);
        }
        System.out.println("Water");
        for(Token token : WaterMaster.GetTokenList()){
            System.out.println(token.Name);
        }
    }
}