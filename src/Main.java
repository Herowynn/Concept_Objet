// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import Elements.*;
import Enums.Types;
import Mapping.*;
import MiniGames.MiniGamesManager;
import Tokens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Map myMap = new Map(51, 14);

        /*Queen rook1 = new Queen(Types.AIR, "Léonard");
        rook1.getCoordinateXY();
        rook1.Move();
        rook1.getCoordinateXY();
        rook1.getEnergyLeft();*/

        List<Token> tokens;
        List<Elemental> masters = new ArrayList<>();

        Air AirMaster = Air.getInstance("AirMaster", Types.AIR, myMap, 5);
        Fire FireMaster = Fire.getInstance("FireMaster", Types.FEU, myMap, 5);
        Water WaterMaster = Water.getInstance("WaterMaster", Types.EAU, myMap, 5);
        Earth EarthMaster = Earth.getInstance("EarthMaster", Types.TERRE, myMap, 5);

        masters.add(AirMaster);
        masters.add(FireMaster);
        masters.add(WaterMaster);
        masters.add(EarthMaster);

        SimulationManager.getInstance(masters);
        tokens = SimulationManager.GetAllTokensFromMasters();
        /*for (Token token : tokens) {
            System.out.println(token.Name);
        }*/

        /*MiniGamesManager mGManager = new MiniGamesManager();
        Random rand = new Random();

        for(int i = 0; i < 10; i++){
            System.out.println(mGManager.playMiniGame(tokens.get(rand.nextInt(tokens.size())), tokens.get(rand.nextInt(tokens.size()))).Name);
        }*/

        /*for(Elemental master : masters){
            master.getCoordinates();
        }*/


        /*for (Box[] boxTab : myMap.MapInfos){
            for(Box box : boxTab){
                if(box.isOcccupiedByMaster())
                    System.out.println(box.isOcccupiedByMaster());
            }
        }*/

        myMap.printMap();


    }
}