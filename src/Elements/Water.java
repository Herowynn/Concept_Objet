package Elements;

import Enums.Types;
import Managers.SimulationManager;
import Mapping.*;

public class Water extends Master {
    private static Water instance;

    public static Water getInstance(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        if(instance == null){
            instance = new Water(name, type, map, nbOfTokens, manager);
        }
        return instance;
    }
    public Water(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        super(name, type, map, nbOfTokens, manager);
        createPercentagesTokens(50, 50);
        createTokens(Types.EAU);
    }
}
