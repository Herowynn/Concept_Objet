package Elements;

import Enums.Types;
import Managers.SimulationManager;
import Mapping.*;

public class Water extends Master {
    private static Water Instance;
    protected String ANSI_Code = "\u001B[34m";

    public static Water getInstance(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        if(Instance == null){
            Instance = new Water(name, type, map, nbOfTokens, manager);
        }
        return Instance;
    }
    public Water(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        super(name, type, map, nbOfTokens, manager);
        CreatePercentagesTokens(50, 50);
        createTokens(Types.EAU);
        System.out.println("création d'un Water Master nommé " + this.name);

    }
}
