package Elements;

import Enums.*;
import MiniGames.*;
import Tokens.*;
import Mapping.*;

import javax.swing.text.Element;
import java.util.*;
import java.util.Map;

public abstract class Elemental {
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

    public Elemental(String name, Types type, Mapping.Map map, int nbOfTokens){
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
            else if(value <= (int)PercentagesCreationToken.values().toArray()[1]){
                // Create type Knight
                //ElementTokens.add(new Knight(type, type.toString() + " Knight" + i));
                ElementTokens.add(new Queen(GameMap, type.toString() + " Knight" + i, this));
            }
            else if(value <= (int)PercentagesCreationToken.values().toArray()[2]){
                // Create type King
                //ElementTokens.add(new King(type, type.toString() + " King" + i);
                ElementTokens.add(new Queen(GameMap, type.toString() + " King" + i, this));
            }
            else {
                ElementTokens.add(new Rook(GameMap, type.toString() + " Rook" + i, this));
            }
        }
    }

    protected void CreatePercentagesTokens(int Bishop, int King, int Knight, int Rook){
        PercentagesCreationToken.put("Bishop", Bishop);
        PercentagesCreationToken.put("King", Bishop + King);
        PercentagesCreationToken.put("Knight", Bishop + King + Knight);
        PercentagesCreationToken.put("Rook", Bishop + Knight + King + Rook);
    }

    public List<Token> GetTokenList(){
        return ElementTokens;
    }

    public void getCoordinates(){
        System.out.println(coordinateX + ", " + coordinateY);
    }
}
