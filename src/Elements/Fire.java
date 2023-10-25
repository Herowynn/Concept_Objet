package Elements;

import Enums.Types;
import Mapping.*;

public class Fire extends Venflamme {
    private static Fire Instance;
    protected String ANSI_Code = "\u001B[31m";

    public static Fire getInstance(String name, Map map){
        if(Instance == null){
            Instance = new Fire(name, map);
        }
        return Instance;
    }

    public Fire(String name, Map map){
        Name = name;
        NumberOfTokens = 5;
        GameMap = map;
        CreatePercentagesTokens(30, 20, 20, 30);
        createTokens(Types.FEU);
        System.out.println("création d'un Fire Master nommé " + Name);

    }
}
