package Elements;

public class Fire extends Venflamme {
    private static Fire Instance;

    public static Fire getInstance(String name){
        if(Instance == null){
            Instance = new Fire(name);
        }
        return Instance;
    }

    public Fire(String name){
        ANSI_Code = "\u001B[31m";
        Name = name;
        //BonusMalusEnergy
        //BonusMalusMovement
        createTokens();
        System.out.println("création d'un Fire Master nommé " + Name);

    }
}
