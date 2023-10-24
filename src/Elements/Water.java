package Elements;

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
        //BonusMalusEnergy
        //BonusMalusMovement
        createTokens();
        System.out.println("création d'un Water Master nommé " + Name);

    }
}
