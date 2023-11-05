package Elements;

import Enums.Types;
import Managers.SimulationManager;
import Mapping.*;

public class Fire extends Master {
    private static Fire instance;

    public static Fire getInstance(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        if(instance == null){
            instance = new Fire(name, type, map, nbOfTokens, manager);
        }
        return instance;
    }

    public Fire(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        super(name, type, map, nbOfTokens, manager);
        createPercentagesTokens(40, 60);
        createTokens(Types.FEU);

    }
}
