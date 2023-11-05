package Elements;

import Enums.*;
import Managers.*;
import Tokens.*;

import java.util.*;
import java.util.Map;

public abstract class Master {
    public String Name;
    protected String ANSI_Code = "\u001B[0m";
    public String ANSI_ResetCode = "\u001B[0m";
    protected int NumberOfTokens;
    protected List<String> MessagesCollected = new ArrayList<String>();
    public Map<String, Integer> BonusMalusEnergy = new Hashtable<String, Integer>();
    public Map<String, Integer> BonusMalusMovement = new Hashtable<String, Integer>();
    protected HashMap<String, Integer> PercentagesCreationToken = new HashMap<String, Integer>();
    protected List<Token> ElementTokens = new ArrayList<Token>();
    protected Mapping.Map GameMap;
    protected MiniGamesManager miniGamesManager;
    protected Types type;
    protected Alliances alliance;
    protected int coordinateX;
    protected int coordinateY;
    protected SimulationManager simulationManager;

    public Master(String name, Types type, Mapping.Map map, int nbOfTokens){
        Name = name;
        this.type = type;
        NumberOfTokens = nbOfTokens;
        GameMap = map;
        miniGamesManager = MiniGamesManager.getInstance();

        if(type == Types.AIR || type == Types.FEU)
            alliance = Alliances.VENFLAMME;
        else
            alliance = Alliances.HYDRATERRE;

        HashSet<String> mySafeZone = GameMap.getBoxesFromMySafeZone(type);

        Random rand = new Random();
        String coordinates = ((String)mySafeZone.toArray()[rand.nextInt(mySafeZone.size())]);
        System.out.println(coordinates);

        coordinateX = Integer.parseInt(coordinates.split(",")[0]);
        coordinateY = Integer.parseInt(coordinates.split(",")[1]);

        GameMap.setMaster(coordinateX, coordinateY, this);
    }

    public void receiveMessagesFromToken(List<String> messages){
        for(String message : messages) {
            if (!MessagesCollected.contains(message))
                MessagesCollected.add(message);
        }
    }
    public void setSimulationManager(SimulationManager manager) {
        simulationManager = manager;
    }

    public void CollectMessages(String[] messagesToCollect){
        for(String message : messagesToCollect){
            if(!MessagesCollected.contains(message)){
                MessagesCollected.add(message);
            }
        }
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

    public int NumberOfMessagesCollected(){
        return MessagesCollected.size();
    }

    protected void createTokens(Types type){
        ElementTokens.add(new Queen(GameMap, type.toString() + " Queen", this));

        Random rand = new Random();
        int value;

        for(int i = 1; i < NumberOfTokens; i++){
            value = rand.nextInt(100);

            if(value <= (int)PercentagesCreationToken.values().toArray()[0]){
                ElementTokens.add(new Bishop(GameMap, type.toString() + " Bishop" + i, this));
            }
            else {
                ElementTokens.add(new Rook(GameMap, type.toString() + " Rook" + i, this));
            }
        }
    }

    protected void CreatePercentagesTokens(int Bishop, int Rook){
        PercentagesCreationToken.put("Bishop", Bishop);
        PercentagesCreationToken.put("Rook", Rook);
    }

    public List<Token> GetTokenList(){
        return ElementTokens;
    }

    public void getCoordinates(){
        System.out.println(coordinateX + ", " + coordinateY);
    }
}
