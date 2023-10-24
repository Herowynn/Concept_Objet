package Elements;

import Enums.Types;

import java.util.*;

public abstract class Elemental {
    public String Name;
    protected String ANSI_Code = "\u001B[0m";
    public String ANSI_ResetCode = "\u001B[0m";
    protected int NumberOfTokens;
    protected List<String> MessagesCollected = new ArrayList<String>();
    public Map<String, Integer> BonusMalusEnergy = new Hashtable<String, Integer>();
    public Map<String, Integer> BonusMalusMovement = new Hashtable<String, Integer>();
    protected Map<String, Integer> PercentagesCreationToken = new Hashtable<String, Integer>();

    public void CollectMessages(String[] messagesToCollect){
        for(String message : messagesToCollect){
            if(!MessagesCollected.contains(message)){
                MessagesCollected.add(message);
            }
        }
    }

    public int NumberOfMessagesCollected(){
        return MessagesCollected.size();
    }

    protected void createTokens(Types type){
        System.out.println("Création des tokens !");

        
    }

    protected void CreatePercentagesTokens(int Bishop, int King, int Knight, int Rook){
        PercentagesCreationToken.put("Bishop", Bishop);
        PercentagesCreationToken.put("Knight", Knight);
        PercentagesCreationToken.put("King", King);
        PercentagesCreationToken.put("Rook", Rook);
    }
}
