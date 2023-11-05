package Managers;

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
        System.out.println("Un Pierre-Papier-Ciseaux a été déclenché entre " + player1.getName() + " et " + player2.getName());
        Random rand = new Random();
        int player1Move = rand.nextInt(3);
        int player2Move = rand.nextInt(3);
        String symbolPlayer1;
        String symbolPlayer2;

        switch(player1Move){
            case 1:
                symbolPlayer1 = "Papier";
                break;
            case 2:
                symbolPlayer1 = "Ciseaux";
                break;
            default:
                symbolPlayer1 = "Pierre";
                break;
        }

        switch(player2Move){
            case 1:
                symbolPlayer2 = "Papier";
                break;
            case 2:
                symbolPlayer2 = "Ciseaux";
                break;
            default:
                symbolPlayer2 = "Pierre";
                break;
        }

        System.out.println("Le " + player1.getName() + " joue " + symbolPlayer1 + " et " + player2.getName() + " joue " + symbolPlayer2);

        if(player1Move == player2Move) {
            System.out.println("égalité ! On recommence !");
            rockPaperScissors(player1, player2);
        }

        if(player1Move == 0 && player2Move == 2){
            System.out.println("Le " + player1.getName() + " a donc gagné ! Bravo à lui !");
            return player1;
        }

        else if (player2Move == 0 && player1Move == 2){
            System.out.println("Le " + player2.getName() + " a donc gagné ! Bravo à lui !");
            return player2;
        }

        else if (player1Move == 1 && player2Move == 0){
            System.out.println("Le " + player1.getName() + " a donc gagné ! Bravo à lui !");
            return player1;
        }

        else if (player2Move == 1 && player1Move == 0){
            System.out.println("Le " + player2.getName() + " a donc gagné ! Bravo à lui !");
            return player2;
        }

        else if (player1Move == 2 && player2Move == 1){
            System.out.println("Le " + player1.getName() + " a donc gagné ! Bravo à lui !");
            return player1;
        }
        else{
            System.out.println("Le " + player2.getName() + " a donc gagné ! Bravo à lui !");
            return player2;
        }
    }

    private Token diceThrow(Token player1, Token player2){
        System.out.println("Un lancé de dés a été déclenché entre " + player1.getName() + " et " + player2.getName());

        Random rand = new Random();
        int player1Value = rand.nextInt(13);
        int player2Value = rand.nextInt(13);

        System.out.println("Le " + player1.getName() + " a fait un " + player1Value + " et le " + player2.getName() + " a fait un " + player2Value);

        if(player1Value == player2Value) {
            System.out.println("Oh non c'est une égalité ! On relance !");
            diceThrow(player1, player2);
        }

        if(player1Value > player2Value){
            System.out.println(player1.getName() + " a donc gagné ! Bravo à lui !");
            return player1;
        }
        else {
            System.out.println(player2.getName() + " a donc gagné ! Bravo à lui !");
            return player2;
        }
    }
}
