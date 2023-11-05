package Elements;

import Enums.*;
import Managers.*;
import Tokens.*;

import java.util.*;

public abstract class Master {
    protected String name;
    protected int numberOfTokens;
    protected List<String> messagesCollected = new ArrayList<>();
    protected HashMap<String, Integer> percentagesCreationToken = new HashMap<>();
    protected List<Token> elementTokens = new ArrayList<>();
    protected Mapping.Map gameMap;
    protected MiniGamesManager miniGamesManager;
    protected Types type;
    protected Alliances alliance;
    protected int coordinateX;
    protected int coordinateY;
    protected SimulationManager simulationManager;

    public Master(String name, Types type, Mapping.Map map, int nbOfTokens, SimulationManager manager){
        simulationManager = manager;
        this.name = name;
        this.type = type;
        numberOfTokens = nbOfTokens;
        gameMap = map;
        miniGamesManager = MiniGamesManager.getInstance();

        if(type == Types.AIR || type == Types.FEU)
            alliance = Alliances.VENFLAMME;
        else
            alliance = Alliances.HYDRATERRE;

        HashSet<String> mySafeZone = gameMap.getBoxesFromMySafeZone(type);

        Random rand = new Random();
        String coordinates = ((String)mySafeZone.toArray()[rand.nextInt(mySafeZone.size())]);
        System.out.println(coordinates);

        coordinateX = Integer.parseInt(coordinates.split(",")[0]);
        coordinateY = Integer.parseInt(coordinates.split(",")[1]);

        gameMap.setMaster(coordinateX, coordinateY, this);

        System.out.println("Le Master de type " + type + " a été créé et positionné en " + coordinateX + ", " + coordinateY);
    }

    public void receiveMessagesFromToken(List<String> messages, Token token){
        for(String message : messages) {
            if (!messagesCollected.contains(message))
                messagesCollected.add(message);
        }
        token.getKnownMessages().clear();
        giveMessagesToToken(5, token);
    }

    public Types getType(){
        return type;
    }

    public Alliances getAlliance(){
        return alliance;
    }

    public MiniGamesManager getMiniGamesManager(){
        return miniGamesManager;
    }

    public int getNumberOfMessagesCollected(){
        return messagesCollected.size();
    }

    protected void createTokens(Types type){
        Queen queen = new Queen(gameMap, type.toString() + " Queen", this);
        giveMessagesToToken(10, queen);
        elementTokens.add(queen);
        System.out.println("Un Queen a été crée " + queen.getName());

        Random rand = new Random();
        int value;

        for(int i = 1; i < numberOfTokens; i++){
            value = rand.nextInt(100);

            if(value <= (int) percentagesCreationToken.values().toArray()[0]){
                Bishop bishop = new Bishop(gameMap, type.toString() + " Bishop" + i, this);
                giveMessagesToToken(10, bishop);
                elementTokens.add(bishop);
                System.out.println("Un Bishop a été crée " + bishop.getName());
            }
            else {
                Rook rook = new Rook(gameMap, type.toString() + " Rook" + i, this);
                giveMessagesToToken(10, rook);
                elementTokens.add(rook);
                System.out.println("Un Rook a été crée " + rook.getName());

            }
        }
    }

    protected void createPercentagesTokens(int Bishop, int Rook){
        percentagesCreationToken.put("Bishop", Bishop);
        percentagesCreationToken.put("Rook", Rook);
    }

    public List<Token> getTokenList(){
        return elementTokens;
    }

    public void giveMessagesToToken(int number, Token token){
        Random rand = new Random();

        for(int i = 0; i < number; i++){
            token.getMessagesFromMaster(simulationManager.getMessageAtIndex(rand.nextInt(simulationManager.getAllPossibleMessagesSize())));
            System.out.println("Le message " + token.getKnownMessages().get(token.getKnownMessages().size() - 1) + " a été transmis à " + token.getName());
        }
    }
}
