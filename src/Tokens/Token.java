package Tokens;

import java.util.*;

import Elements.*;
import Enums.*;
import Mapping.SafeBox;

public abstract class Token {
    public Types Type;
    protected Alliances alliance;
    public List<String> knownMessages = new ArrayList<>();
    public String Name;
    public double EnergyLeft;
    public double EnergyMax;
    public double MovementPrice;
    public double MinMovementPrice;
    public double MaxMovementPrice;
    protected int coordinateX;
    protected int coordinateY;
    public Directions LastDirection;
    protected Mapping.Map GameMap;
    protected Master master;
    protected String letterForMapDisplay;

    public void EnergyRegeneration() {
        EnergyLeft = EnergyMax;
        sendMessagesToMaster();
    }

    public void sendMessagesToMaster() {
        master.receiveMessagesFromToken(knownMessages, this);
    }

    public Token(Mapping.Map map, String name, Master master) {
        GameMap = map;
        this.master = master;
        Type = master.getType();
        alliance = master.getAlliance();
        Name = name;

        Random rand = new Random();
        coordinateX = rand.nextInt(GameMap.SizeX);
        coordinateY = rand.nextInt(GameMap.SizeY);

        while (GameMap.getMapInfo()[coordinateX][coordinateY].isOccupiedByToken()
                || GameMap.getMapInfo()[coordinateX][coordinateY].isOccupiedByMaster()
                || (GameMap.getMapInfo()[coordinateX][coordinateY].isSafeZone()
                && ((SafeBox) GameMap.getMapInfo()[coordinateX][coordinateY]).getType() != Type)
                || GameMap.getMapInfo()[coordinateX][coordinateY].isBlockedByObstacle()) {
            coordinateX = rand.nextInt(GameMap.SizeX);
            coordinateY = rand.nextInt(GameMap.SizeY);
        }

        GameMap.setOccupied(coordinateX, coordinateY, true, this);
    }

    public void MoveToFindMessages() {
        verifyBoxes();
    }

    public void Move() {
        if (this.EnergyLeft >= 0.20 * EnergyMax) {
            MoveToFindMessages();

        } else if (this.EnergyLeft == 0) {
            // If the token does not have enough energy, it becomes an obstacle
            GameMap.getMapInfo()[coordinateX][coordinateY].setObstacle();
            GameMap.getMapInfo()[coordinateX][coordinateY].setOccupied(false, null);

        }
        else {
            GameMap.safeZonePathFinder(coordinateX, coordinateY, this.Type);
        }
    }

    public String getLetterForMapDisplay() {
        return letterForMapDisplay;
    }

    // Check if there is another player around for the message exchange
    protected void verifyBoxes() {
        if (GameMap.getMapInfo()[coordinateX][coordinateY].isSafeZone()) {
            EnergyRegeneration();
        } else {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x != 0 && y != 0 && coordinateX + x < GameMap.SizeX && coordinateY + y < GameMap.SizeY && coordinateX + x >= 0 && coordinateY + y >= 0) {
                        if (GameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken() != null && Type == GameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken().Type) {
                            MessagesExchangeBetweenSameTypes(
                                    GameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken());
                        } else if (GameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken() != null && alliance == GameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken().alliance) {
                            MessagesExchangeBetweenAllies(
                                    GameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken());
                        } else if (GameMap.getMapInfo()[coordinateX + x][coordinateY + y].isOccupiedByToken()) {
                            MessagesExchangeBetweenEnemies(
                                    GameMap.getMapInfo()[coordinateX + x][coordinateY + y].getToken());
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
        System.out.println("The energy of the token is: " + this.EnergyLeft);
        return EnergyLeft;
    }

    // The choice of the direction will be done randomly at each move
    public void ChoixDirection() {
        // We put all the direction in a list
        Directions[] directions = Directions.values();

        Random random = new Random();

        // Generation of a random number to choose the direction in the list
        int indice = random.nextInt(directions.length);

        // Selection of the direction corresponding to the random number
        Directions randomDirection = directions[indice];

        // Print the chosen direction
        System.out.println("The token will take the direction " + randomDirection);
    }

    // Method for moving from a random number of cases.
    public int generateRandomNumberOfCase() {
        Random random = new Random();
        int min = -5;
        int max = 5;

        // Generate a random number between -5 and 5, inclusive
        int randomNumber = random.nextInt((max - min) + 1) + min;
        System.out.println("The random number is: " + randomNumber);
        return randomNumber;
    }

    // This method calcul the last direction of the token according to the
    // coordinates of moving
    // coordinatesX et coordinatesY are the coordinates the token will take at his
    // future move
    public Directions calculTheLastDirection(int coordinateX, int coordinateY) {
        Directions lastDirection;
        if (coordinateX > 0 && coordinateY > 0) {
            lastDirection = Directions.NE;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX > 0 && coordinateY < 0) {
            lastDirection = Directions.SE;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX < 0 && coordinateY < 0) {
            lastDirection = Directions.SW;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX < 0 && coordinateY > 0) {
            lastDirection = Directions.NW;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX == 0 && coordinateY > 0) {
            lastDirection = Directions.N;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX == 0 && coordinateY < 0) {
            lastDirection = Directions.S;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX < 0 && coordinateY == 0) {
            lastDirection = Directions.W;
            System.out.println("The last direction was " + lastDirection);
        } else if (coordinateX > 0 && coordinateY == 0) {
            lastDirection = Directions.E;
            System.out.println("The last direction was " + lastDirection);
        } else {
            lastDirection = null;
            System.out.println("The last direction was " + lastDirection);
        }

        return lastDirection;
    }

    public void MessageExchangeWithLoser(int numberOfMessages, Token sender, Token receiver) {
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
                }
            }
        }
    }

    public void MessagesExchangeBetweenAllies(Token otherPlayer) {
        Random rand = new Random();
        int value;
        String message;

        for (int i = 0; i < 2; i++) {
            value = rand.nextInt(knownMessages.size());
            message = knownMessages.get(value);

            if (!otherPlayer.knownMessages.contains(message))
                otherPlayer.knownMessages.add(knownMessages.get(value));
        }

        for (int i = 0; i < 5; i++) {
            value = rand.nextInt(otherPlayer.knownMessages.size());
            message = otherPlayer.knownMessages.get(value);

            if (!knownMessages.contains(message))
                knownMessages.add(otherPlayer.knownMessages.get(value));
        }
    }

    public void MessagesExchangeBetweenSameTypes(Token otherPlayer) {
        for (int i = 0; i < knownMessages.size() - 1; i++) {
            if (!otherPlayer.knownMessages.contains(knownMessages.get(i)))
                otherPlayer.knownMessages.add(knownMessages.get(i));
        }

        for (int i = 0; i < otherPlayer.knownMessages.size() - 1; i++) {
            if (!knownMessages.contains(otherPlayer.knownMessages.get(i)))
                knownMessages.add(otherPlayer.knownMessages.get(i));
        }
    }

    public void MessagesExchangeBetweenEnemies(Token otherPlayer) {
        Token loser;
        loser = master.getMiniGamesManager().playMiniGame(this, otherPlayer);

        if (loser == this) {
            MessageExchangeWithLoser(3, this, otherPlayer);
            System.out.println(this.Name + " s'est fait battre par " + loser.Name);
        } else {
            MessageExchangeWithLoser(3, otherPlayer, this);
            System.out.println(loser.Name + " s'est fait battre par " + this.Name);
        }
    }

    public void getMessagesFromMaster(String message) {
        knownMessages.add(message);
    }

}
