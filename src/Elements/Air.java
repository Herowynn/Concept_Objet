package Elements;

import Enums.*;
import Mapping.*;

public class Air extends Venflamme {
    private static Air Instance;
    public Map currentMap;

    protected String ANSI_Code = "\u001B[35m";

    public static Air getInstance(String name, Map map){
        if(Instance == null){
            Instance = new Air(name, map);
        }
        return Instance;
    }

    public Air(String name, Map map){
        Name = name;
        NumberOfTokens = 5;
        GameMap = map;
        CreatePercentagesTokens(15, 35, 10, 40);
        System.out.println("création d'un Air Master nommé " + Name);
        createTokens(Types.AIR);
    }

    public void AddMap(Map map){
        currentMap = map;
    }

    public void PrintMap(){
        currentMap.printMap();
    }


}
