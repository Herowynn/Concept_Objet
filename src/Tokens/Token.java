package Tokens;

import java.util.*;

import Elements.*;
import Enums.*;
import Mapping.SafeBox;

public abstract class Token {
    protected Types type;
    protected Alliances alliance;
    protected List<String> knownMessages = new ArrayList<>();
    protected String name;
    protected double energyLeft;
    protected double energyMax;
    protected double movementPrice;
    protected double minMovementPrice;
    protected double maxMovementPrice;
    protected int coordinateX;
    protected int coordinateY;
    protected Directions lastDirection;
    protected Mapping.Map gameMap;
    protected Master master;
    protected String letterForMapDisplay;

    public void EnergyRegeneration() {
        energyLeft = energyMax;
        sendMessagesToMaster();
    }

    public void sendMessagesToMaster() {
        master.receiveMessagesFromToken(knownMessages, this);
    }

    public Token(Mapping.Map map, String name, Master master) {
        gameMap = map;
        this.master = master;
        type = master.getType();
        alliance = master.getAlliance();
        this.name = name;

        Random rand = new Random();
        coordinateX = rand.nextInt(gameMap.sizeX);
        coordinateY = rand.nextInt(gameMap.sizeY);

        while (gameMap.getMapInfo()[coordinateX][coordinateY].isOccupiedByToken()
                || gameMap.getMapInfo()[coordinateX][coordinateY].isOccupiedByMaster()
                || (gameMap.getMapInfo()[coordinateX][coordinateY].isSafeZone()
                && ((SafeBox) gameMap.getMapInfo()[coordinateX][coordinateY]).getType() != type)
                || gameMap.getMapInfo()[coordinateX][coordinateY].isBlockedByObstacle()) {
            coordinateX = rand.nextInt(gameMap.sizeX);
            coordinateY = rand.nextInt(gameMap.sizeY);
        }

        gameMap.setOccupied(coordinateX, coordinateY, true, this);
    }

    public void moveToFindMessages() {
        verifyBoxes();
    }

    public void move() {
        if (energyLeft >= 0.20 * energyMax) {
            moveToFindMessages();

        } else if (energyLeft == 0) {
            // If the token does not have enough energy, it becomes an obstacle
            gameMap.getMapInfo()[coordinateX][coordinateY].setObstacle();
            gameMap.getMapInfo()[coordinateX][coordinateY].setOccupied(false, null);

        }
        else {
            gameMap.safeZonePathFinder(coordinateX, coordinateY, this.type);
        }
    }

    public String getLetterForMapDisplay() {
        return letterForMapDisplay;
    }

    // Check if there is another player around for the message exchange
    protected void verifyBoxes() {
        if (gameMap.getMapInfo()[coordinateX][coordinateY].isSafeZone()) {
            EnergyRegeneration();
        } else {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x != 0 && y != 0 && coordinateX + x < gameMap.sizeX && coordinateY + y < gameMap.sizeY && coordinateX + x >= 0 && coordinateY + y >= 0) {
                        if (gameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken() != null && type == gameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken().type) {
                            messagesExchangeBetweenSameTypes(
                                    gameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken());
                        } else if (gameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken() != null && alliance == gameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken().alliance) {
                            messagesExchangeBetweenAllies(
                                    gameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken());
                        } else if (gameMap.getMapInfo()[coordinateX + x][coordinateY + y].isOccupiedByToken()) {
                            messagesExchangeBetweenEnemies(
                                    gameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken());
                        }
                    }
                }
            }
        }
    }

    public Map<String, Integer> getCoordinateXY() {
        Map<String, Integer> coordinatesXY = new HashMap<>();
        // Ajoute des valeurs pour les coordonnées X et Y
        coordinatesXY.put("coordinateX", coordinateX);
        coordinatesXY.put("coordinateY", coordinateY);
        System.out.println(coordinatesXY);
        return coordinatesXY;
    }

    public double getEnergyLeft() {
        System.out.println("The energy of the token is: " + this.energyLeft);
        return energyLeft;
    }

    // The choice of the direction will be done randomly at each move
    public void choixDirection() {
        // We put all the direction in a list
        Directions[] directions = Directions.values();

        Random random = new Random();

        // Generation of a random number to choose the direction in the list
        int index = random.nextInt(directions.length);

        // Selection of the direction corresponding to the random number
        Directions randomDirection = directions[index];
    }

    // Method for moving from a random number of cases.
    public int generateRandomNumberOfCase() {
        Random random = new Random();
        int min = -5;
        int max = 5;

        // Generate a random number between -5 and 5, inclusive
        return random.nextInt((max - min) + 1) + min;
    }

    // This method calculate the last direction of the token according to the
    // coordinates of moving
    // coordinatesX et coordinatesY are the coordinates the token will take at his
    // future move
    public Directions calculateTheLastDirection(int coordinateX, int coordinateY) {
        Directions lastDirection;
        if (coordinateX > 0 && coordinateY > 0) {
            lastDirection = Directions.NE;
        } else if (coordinateX > 0 && coordinateY < 0) {
            lastDirection = Directions.SE;
        } else if (coordinateX < 0 && coordinateY < 0) {
            lastDirection = Directions.SW;
        } else if (coordinateX < 0 && coordinateY > 0) {
            lastDirection = Directions.NW;
        } else if (coordinateX == 0 && coordinateY > 0) {
            lastDirection = Directions.N;
        } else if (coordinateX == 0 && coordinateY < 0) {
            lastDirection = Directions.S;
        } else if (coordinateX < 0 && coordinateY == 0) {
            lastDirection = Directions.W;
        } else if (coordinateX > 0 && coordinateY == 0) {
            lastDirection = Directions.E;
        } else {
            lastDirection = null;
        }

        return lastDirection;
    }

    public void messageExchangeWithLoser(int numberOfMessages, Token sender, Token receiver) {
        System.out.println(sender.getName() + " donnera donc " + numberOfMessages + " à " + receiver);
        Random rand = new Random();
        String messageToExchange;

        if (!sender.knownMessages.isEmpty()) {
            for (int i = 0; i < numberOfMessages; i++) {
                if(!sender.knownMessages.isEmpty()) {
                    messageToExchange = sender.knownMessages.get(rand.nextInt(sender.knownMessages.size()));

                    if (receiver.knownMessages.contains(messageToExchange)) {
                        messageToExchange = sender.knownMessages.get(rand.nextInt(sender.knownMessages.size()));
                    }

                    receiver.knownMessages.add(messageToExchange);
                    sender.knownMessages.remove(messageToExchange);
                    System.out.println("Les deux pions viennent d'échanger le message suivant : " + messageToExchange);
                }
            }
        }
    }

    public void messagesExchangeBetweenAllies(Token otherPlayer) {
        System.out.println(name + " échange 2 messages avec son allié " + otherPlayer.name);

        Random rand = new Random();
        int value;
        String messageToExchange;

        for (int i = 0; i < 2; i++) {
            value = rand.nextInt(knownMessages.size());
            messageToExchange = knownMessages.get(value);
            
            System.out.println("Les deux pions viennent d'échanger le message suivant : " + messageToExchange);

            if (!otherPlayer.knownMessages.contains(messageToExchange))
                otherPlayer.knownMessages.add(knownMessages.get(value));
        }

        for (int i = 0; i < 5; i++) {
            value = rand.nextInt(otherPlayer.knownMessages.size());
            messageToExchange = otherPlayer.knownMessages.get(value);
            System.out.println("Les deux pions viennent d'échanger le message suivant : " + messageToExchange);

            if (!knownMessages.contains(messageToExchange))
                knownMessages.add(otherPlayer.knownMessages.get(value));
        }
    }

    public void messagesExchangeBetweenSameTypes(Token otherPlayer) {
        System.out.println(this.name + " et " + otherPlayer.name + " mettent en commun leus savoir !");
        for (int i = 0; i < knownMessages.size() - 1; i++) {
            if (!otherPlayer.knownMessages.contains(knownMessages.get(i)))
                otherPlayer.knownMessages.add(knownMessages.get(i));

            System.out.println(otherPlayer.name + " vient de recevoir le message suivant " + knownMessages.get(i));
        }

        for (int i = 0; i < otherPlayer.knownMessages.size() - 1; i++) {
            if (!knownMessages.contains(otherPlayer.knownMessages.get(i)))
                knownMessages.add(otherPlayer.knownMessages.get(i));

            System.out.println(name + " vient de recevoir le message suivant " + otherPlayer.knownMessages.get(i));
        }
    }

    public void messagesExchangeBetweenEnemies(Token otherPlayer) {
        Token loser;
        loser = master.getMiniGamesManager().playMiniGame(this, otherPlayer);

        if (loser == this) {
            messageExchangeWithLoser(3, this, otherPlayer);
            System.out.println(this.name + " s'est fait battre par " + loser.name);
        } else {
            messageExchangeWithLoser(3, otherPlayer, this);
            System.out.println(loser.name + " s'est fait battre par " + this.name);
        }
    }

    public void getMessagesFromMaster(String message) {
        knownMessages.add(message);
    }

    public List<String> getKnownMessages(){
        return knownMessages;
    }

    public Types getType() {
        return type;
    }

    public String getName(){
        return name;
    }
}
