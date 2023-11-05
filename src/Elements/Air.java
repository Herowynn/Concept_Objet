package Elements;

import Enums.*;
import Managers.SimulationManager;
import Mapping.*;

public class Air extends Master {
    private static Air instance;

    public static Air getInstance(String name, Types type,Map map, int nbOfTokens, SimulationManager manager){
        if(instance == null){
            instance = new Air(name, type, map, nbOfTokens, manager);
        }
        return instance;
    }

    public Air(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        super(name, type, map, nbOfTokens, manager);
        createPercentagesTokens(20, 80);
        createTokens(Types.AIR);
    }
}
