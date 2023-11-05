package Managers;

import Elements.*;
import Enums.Types;
import Tokens.*;

import java.util.*;

public class SimulationManager {
    private static SimulationManager instance;
    private static List<Token> allTokens = new ArrayList<>();
    private List<Token> tokensToPlay = new ArrayList<>();
    private List<Master> masters = new ArrayList<>();
    private Mapping.Map gameMap;

    private static final List<String> allPossibleMessages = new ArrayList<>();

    public static SimulationManager getInstance(Mapping.Map myMap) {
        if (instance == null) {
            instance = new SimulationManager(myMap);
        }
        return instance;
    }

    private SimulationManager(Mapping.Map myMap){
        gameMap = myMap;
        createMessagesList();
        createMasters(myMap);
    }

    private void createMasters(Mapping.Map myMap){
        Air AirMaster = Air.getInstance("AirMaster", Types.AIR, myMap, 5, this);
        Fire FireMaster = Fire.getInstance("FireMaster", Types.FEU, myMap, 5, this);
        Water WaterMaster = Water.getInstance("WaterMaster", Types.EAU, myMap, 5, this);
        Earth EarthMaster = Earth.getInstance("EarthMaster", Types.TERRE, myMap, 5, this);

        masters.add(AirMaster);
        masters.add(FireMaster);
        masters.add(WaterMaster);
        masters.add(EarthMaster);

        setTokens();
    }

    public void setTokens(){
        for (Master master : masters) {
            allTokens.addAll(master.GetTokenList());
        }
    }

    private void createMessagesList(){
        String str = "ABCDE";
        findPermutations(str);
    }

    private static void swap(char[] chars, int i, int j)
    {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // Fonction récursif pour générer toutes les permutations d'une string
    private static void permutations(char[] chars, int currentIndex)
    {
        if (currentIndex == chars.length - 1) {
            allPossibleMessages.add(String.valueOf(chars));
        }

        for (int i = currentIndex; i < chars.length; i++)
        {
            swap(chars, currentIndex, i);
            permutations(chars, currentIndex + 1);
            swap(chars, currentIndex, i);
        }
    }

    public static void findPermutations(String str) {

        // cas de base
        if (str == null || str.isEmpty()) {
            return;
        }

        permutations(str.toCharArray(), 0);
    }

    /*public static List<Token> getAllTokensFromMasters() {
        return allTokens;
    }*/

    public void launchSimulation(){
        Random rand = new Random();
        int indexValue;

        gameMap.printMap();

        for(int i = 0; i < 1; i++){
            tokensToPlay = allTokens;

            while(!tokensToPlay.isEmpty()){
                indexValue = rand.nextInt(tokensToPlay.size());
                tokensToPlay.get(indexValue).Move();
                tokensToPlay.remove(indexValue);
            }

            gameMap.printMap();
        }
    }

    public String getMessageAtIndex(int index){
        return allPossibleMessages.get(index);
    }

    public int getAllPossibleMessagesSize(){
        return allPossibleMessages.size();
    }

}
