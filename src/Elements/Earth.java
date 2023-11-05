package Elements;

import Enums.Types;
import Managers.*;
import Mapping.Map;

public class Earth extends Master {
    private static Earth instance;

    public static Earth getInstance(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        if(instance == null){
            instance = new Earth(name, type, map, nbOfTokens, manager);
        }
        return instance;
    }

    public Earth(String name, Types type, Map map, int nbOfTokens, SimulationManager manager){
        super(name, type, map, nbOfTokens, manager);
        createPercentagesTokens(70, 30);
        createTokens(Types.TERRE);
    }
}
