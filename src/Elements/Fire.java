package Elements;

import Enums.Types;

public class Fire extends Venflamme {
    private static Fire Instance;
    protected String ANSI_Code = "\u001B[31m";

    public static Fire getInstance(String name){
        if(Instance == null){
            Instance = new Fire(name);
        }
        return Instance;
    }

    public Fire(String name){
        Name = name;
        NumberOfTokens = 5;
        CreatePercentagesTokens(30, 20, 20, 30);
        createTokens(Types.FEU);
        System.out.println("création d'un Fire Master nommé " + Name);

    }
}
