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
    private int delayBetweenTurns; // en milli-secondes

    private int nbOfTurnsToPlay;

    private static final List<String> allPossibleMessages = new ArrayList<>();

    public static SimulationManager getInstance(Mapping.Map myMap, int nbOfTurn, int delayBetweenTurns) {
        if (instance == null) {
            instance = new SimulationManager(myMap, nbOfTurn, delayBetweenTurns);
        }
        return instance;
    }

    private SimulationManager(Mapping.Map myMap, int nbOfTurn, int delayBetweenTurns){
        this.delayBetweenTurns = delayBetweenTurns;
        nbOfTurnsToPlay = nbOfTurn;
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
            allTokens.addAll(master.getTokenList());
        }
    }

    public void launchSimulation() throws InterruptedException {
        Random rand = new Random();
        int indexValue;

        System.out.println("Initialization");

        gameMap.printMap();

        Master winner = null;

        for(int i = 0; i < nbOfTurnsToPlay; i++){
            tokensToPlay.addAll(allTokens);

            System.out.println("Turn n°" + i);

            while(!tokensToPlay.isEmpty()){
                indexValue = rand.nextInt(tokensToPlay.size());
                tokensToPlay.get(indexValue).move();
                tokensToPlay.remove(indexValue);
            }

            gameMap.printMap();

            for (Master master : masters){
                if(master.getNumberOfMessagesCollected() == 120){
                    winner = master;
                    break;
                }
            }

            if(winner != null)
                break;

            Thread.sleep(delayBetweenTurns);
        }

        if (winner == null) {
            for (Master master : masters){
                if(winner == null || master.getNumberOfMessagesCollected() > winner.getNumberOfMessagesCollected())
                    winner = master;
            }
        }


        Victory(winner, nbOfTurnsToPlay);
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

    public String getMessageAtIndex(int index){
        return allPossibleMessages.get(index);
    }

    public int getAllPossibleMessagesSize(){
        return allPossibleMessages.size();
    }

    public void Victory(Master winner, int nbOfTurn){
        System.out.println("L'équipe " + winner.getType() + " a gagné la partie au bout de  " + nbOfTurn +
                " tours en ayant collecté " + winner.getNumberOfMessagesCollected() + " messages au total !");
        System.out.println("Félicitations aux vainqueurs !");
    }

}
