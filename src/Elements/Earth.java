package Elements;

import Enums.Types;
import Managers.*;
import Mapping.Map;

public class Earth extends Master {
    private static Earth Instance;
    protected String ANSI_Code = "\u001B[32m";

    public static Earth getInstance(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        if(Instance == null){
            Instance = new Earth(name, type, map, nbOfTokens, manager);
        }
        return Instance;
    }

    public Earth(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        super(name, type, map, nbOfTokens, manager);
        CreatePercentagesTokens(70, 30);
        createTokens(Types.TERRE);
        System.out.println("création d'un Earth Master nommé " + this.name);
    }
}
