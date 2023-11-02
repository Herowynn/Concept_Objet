package MiniGames;

import Tokens.*;

import java.util.Random;

public class MiniGamesManager {
    private static MiniGamesManager instance;

    public static MiniGamesManager getInstance(){
        if(instance == null){
            instance = new MiniGamesManager();
        }
        return instance;
    }

    public Token playMiniGame(Token player1, Token player2){
        Random rand = new Random();

        return switch (rand.nextInt(2)) {
            case 0 -> rockPaperScissors(player1, player2);
            case 1 -> diceThrow(player1, player2);
            default -> null;
        };
    }

    private Token rockPaperScissors(Token player1, Token player2){
        System.out.println("On jour au pierre papier ciseaux !");
        Random rand = new Random();
        int player1Move = rand.nextInt(3);
        int player2Move = rand.nextInt(3);

        if(player1Move == player2Move) {
            rockPaperScissors(player1, player2);
        }

        if(player1Move == 0 && player2Move == 2){
            System.out.println("Pierre vs Ciseaux !");
            return player1;
        }

        else if (player2Move == 0 && player1Move == 2){
            System.out.println("Ciseaux vs Pierre !");
            return player2;
        }

        else if (player1Move == 1 && player2Move == 0){
            System.out.println("Papier vs Pierre !");
            return player1;
        }

        else if (player2Move == 1 && player1Move == 0){
            System.out.println("Pierre vs Papier !");
            return player2;
        }

        else if (player1Move == 2 && player2Move == 1){
            System.out.println("Ciseaux vs Papier !");
            return player1;
        }
        else{
            System.out.println("Papier vs Ciseaux !");
            return player2;
        }
    }

    private Token diceThrow(Token player1, Token player2){
        System.out.println("On joue au lancé de dés !");
        Random rand = new Random();
        int player1Value = rand.nextInt(13);
        int player2Value = rand.nextInt(13);

        System.out.println("Player1 = " + player1Value + " vs Player2 = " + player2Value);

        if(player1Value == player2Value)
            diceThrow(player1, player2);

        if(player1Value > player2Value)
            return player1;
        else
            return player2;
    }
}
