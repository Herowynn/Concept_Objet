package Elements;

import Enums.Types;
import Managers.SimulationManager;
import Mapping.*;

public class Fire extends Master {
    private static Fire Instance;
    protected String ANSI_Code = "\u001B[31m";

    public static Fire getInstance(String name, Types type, Map map, int nbOfTokens){
        if(Instance == null){
            Instance = new Fire(name, type, map, nbOfTokens);
        }
        return Instance;
    }

    public Fire(String name, Types type, Map map, int nbOfTokens){
        super(name, type, map, nbOfTokens);
        CreatePercentagesTokens(40, 60);
        createTokens(Types.FEU);
        System.out.println("création d'un Fire Master nommé " + Name);

    }
}
