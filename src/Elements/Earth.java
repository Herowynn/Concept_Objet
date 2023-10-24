package Elements;

public class Earth extends Hydraterre {
    private static Earth Instance;

    public static Earth getInstance(String name){
        if(Instance == null){
            Instance = new Earth(name);
        }
        return Instance;
    }

    public Earth(String name){
        ANSI_Code = "\u001B[32m";
        Name = name;
        //BonusMalusEnergy
        //BonusMalusMovement
        createTokens();
        System.out.println("création d'un Earth Master nommé " + Name);
    }
}
