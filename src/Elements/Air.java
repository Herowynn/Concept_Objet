package Elements;

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
        createTokens();
        System.out.println("création d'un Air Master nommé " + Name);

    }


}
