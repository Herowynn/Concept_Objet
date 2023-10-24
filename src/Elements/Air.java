package Elements;

import Enums.Types;

public class Air extends Venflamme {
    private static Air Instance;

    public static Air getInstance(String name){
        if(Instance == null){
            Instance = new Air(name);
        }
        return Instance;
    }

    public Air(String name){
        ANSI_Code = "\u001B[35m";
        Name = name;
        CreatePercentagesTokens(15, 35, 10, 40);
        createTokens(Types.AIR);
        System.out.println("création d'un Air Master nommé " + Name);
    }


}
