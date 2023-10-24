package Elements;

import Enums.Types;
import Tokens.*;

import javax.swing.text.Element;
import java.util.*;

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

        for(int i = 0; i < PercentagesCreationToken.size(); i++){
            System.out.println(PercentagesCreationToken.values().toArray()[i]);
        }

        ElementTokens.add(new Queen(type, type.toString() + " Queen"));

        Random rand = new Random();
        int value;

        for(int i = 1; i < NumberOfTokens; i++){
            value = rand.nextInt(100);
            System.out.println(value);

            if(value <= (int)PercentagesCreationToken.values().toArray()[0]){
                ElementTokens.add(new Bishop(type, type.toString() + " Bishop" + i));
                System.out.println("J'ai créé un Bishop !");
            }
            else if(value <= (int)PercentagesCreationToken.values().toArray()[1]){
                // Create type Knight
                //ElementTokens.add(new Knight(type, type.toString() + " Knight" + i));
                ElementTokens.add(new Queen(type, type.toString() + " Knight" + i));
                System.out.println("J'ai créé un Knight !");
            }
            else if(value <= (int)PercentagesCreationToken.values().toArray()[2]){
                // Create type King
                //ElementTokens.add(new King(type, type.toString() + " King" + i);
                ElementTokens.add(new Queen(type, type.toString() + " King" + i));
                System.out.println("J'ai créé un King !");
            }
            else {
                ElementTokens.add(new Rook(type, type.toString() + " Rook" + i));
                System.out.println("J'ai créé un Rook !");
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
}
