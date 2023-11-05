package Managers;

import Elements.*;
import Tokens.*;

import java.util.*;

public class SimulationManager {
    private static SimulationManager instance;
    private static List<Token> allTokens = new ArrayList<>();
    private List<Token> tokensToPlay = new ArrayList<>();

    private static final List<String> allPossibleMessages = new ArrayList<>();

    public static SimulationManager getInstance(List<Master> masters) {
        if (instance == null) {
            instance = new SimulationManager(masters);
        }
        return instance;
    }

    private SimulationManager(List<Master> masters){

        for (Master master : masters) {
            allTokens.addAll(master.GetTokenList());
            master.setSimulationManager(instance);
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

        for(int i = 0; i < 1; i++){
            tokensToPlay = allTokens;

            while(!tokensToPlay.isEmpty()){
                System.out.println("Je suis dans le while reste " + tokensToPlay + " tokens qui doivent jouer");
                indexValue = rand.nextInt(tokensToPlay.size());
                tokensToPlay.get(indexValue).Move();
                tokensToPlay.remove(indexValue);
            }

        }
    }

    public String getMessageAtIndex(int index){
        return allPossibleMessages.get(index);
    }

}
