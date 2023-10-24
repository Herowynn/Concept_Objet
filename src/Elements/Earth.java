package Elements;

import Enums.Types;

public class Earth extends Hydraterre {
    private static Earth Instance;
    protected String ANSI_Code = "\u001B[32m";

    public static Earth getInstance(String name){
        if(Instance == null){
            Instance = new Earth(name);
        }
        return Instance;
    }

    public Earth(String name){
        Name = name;
        NumberOfTokens = 5;
        CreatePercentagesTokens(30, 20, 10, 40);
        createTokens(Types.TERRE);
        System.out.println("création d'un Earth Master nommé " + Name);
    }
}
