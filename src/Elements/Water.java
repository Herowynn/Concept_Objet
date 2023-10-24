package Elements;

import Enums.Types;

public class Water extends Hydraterre {
    private static Water Instance;

    public static Water getInstance(String name){
        if(Instance == null){
            Instance = new Water(name);
        }
        return Instance;
    }
    public Water(String name){
        ANSI_Code = "\u001B[34m";
        Name = name;
        CreatePercentagesTokens(25, 25, 25, 25);
        createTokens(Types.EAU);
        System.out.println("création d'un Water Master nommé " + Name);

    }
}
