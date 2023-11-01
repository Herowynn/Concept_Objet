package Elements;

import Enums.Types;
import Mapping.*;

public class Water extends Hydraterre {
    private static Water Instance;
    protected String ANSI_Code = "\u001B[34m";

    public static Water getInstance(String name, Map map, int nbOfTokens){
        if(Instance == null){
            Instance = new Water(name, map, nbOfTokens);
        }
        return Instance;
    }
    public Water(String name, Map map, int nbOfTokens){
        super(name, map, nbOfTokens);
        CreatePercentagesTokens(25, 25, 25, 25);
        createTokens(Types.EAU);
        System.out.println("création d'un Water Master nommé " + Name);

    }
}
