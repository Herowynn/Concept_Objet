package Elements;

import Enums.Types;
import Mapping.Map;

public class Earth extends Hydraterre {
    private static Earth Instance;
    protected String ANSI_Code = "\u001B[32m";

    public static Earth getInstance(String name, Types type, Map map, int nbOfTokens){
        if(Instance == null){
            Instance = new Earth(name, type, map, nbOfTokens);
        }
        return Instance;
    }

    public Earth(String name, Types type, Map map, int nbOfTokens){
        super(name, type, map, nbOfTokens);
        CreatePercentagesTokens(30, 20, 10, 40);
        createTokens(Types.TERRE);
        System.out.println("création d'un Earth Master nommé " + Name);
    }
}
