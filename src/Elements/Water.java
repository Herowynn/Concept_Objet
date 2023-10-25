package Elements;

import Enums.Types;
import Mapping.*;

public class Water extends Hydraterre {
    private static Water Instance;
    protected String ANSI_Code = "\u001B[34m";

    public static Water getInstance(String name, Map map){
        if(Instance == null){
            Instance = new Water(name, map);
        }
        return Instance;
    }
    public Water(String name, Map map){
        Name = name;
        NumberOfTokens = 5;
        GameMap = map;
        CreatePercentagesTokens(25, 25, 25, 25);
        createTokens(Types.EAU);
        System.out.println("création d'un Water Master nommé " + Name);

    }
}
