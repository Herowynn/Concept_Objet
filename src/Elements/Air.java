package Elements;

import Enums.*;
import Managers.SimulationManager;
import Mapping.*;

public class Air extends Master {
    private static Air Instance;
    public Map currentMap;

    protected String ANSI_Code = "\u001B[35m";

    public static Air getInstance(String name, Types type,Map map, int nbOfTokens, SimulationManager manager){
        if(Instance == null){
            Instance = new Air(name, type, map, nbOfTokens, manager);
        }
        return Instance;
    }

    public Air(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        super(name, type, map, nbOfTokens, manager);
        CreatePercentagesTokens(20, 80);
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
