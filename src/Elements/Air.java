package Elements;

import Enums.Types;

public class Air extends Venflamme {
    private static Air Instance;

    protected String ANSI_Code = "\u001B[35m";

    public static Air getInstance(String name){
        if(Instance == null){
            Instance = new Air(name);
        }
        return Instance;
    }

    public Air(String name){
        Name = name;
        NumberOfTokens = 5;
        CreatePercentagesTokens(15, 35, 10, 40);
        System.out.println("création d'un Air Master nommé " + Name);
        createTokens(Types.AIR);
    }


}
