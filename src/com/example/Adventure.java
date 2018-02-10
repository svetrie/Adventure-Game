package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Adventure {
    private static final String DEFAULT_JSON_FILE_URL = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
    private static final Scanner scan = new Scanner(System.in);

    //Represents possible actions user can take
    private static final String TAKE_ITEM = "take";
    private static final String DROP_ITEM = "drop";
    private static final String GO_DIRECTION = "go";
    private static final String QUIT_GAME = "quit";
    private static final String EXIT_GAME = "exit";
    private static final String DISPLAY_ITEMS = "list";
    private static final String PLAYER_INFO = "playerinfo";
    private static final String DUEL_MONSTER = "duel";

    //Represents possible actions user can take during a duel
    private static final String ATTACK = "attack";
    private static final String ATTACK_WITH_ITEM = "attackwith";
    private static final String DISENGAGE = "disengage";
    private static final String STATUS = "status";

    private GameWorld gameWorld;
    private Player player;
    private String startingRoomName;
    private String endingRoomName;
    private Room currentRoom;

    public static void main(String[] args) {
        Adventure adventure;

        if (args.length > 0) {
            System.out.println("Using " + args[0]);
            adventure = new Adventure(JsonFileLoader.parseJsonFileUsingFilePath(args[0]));
        } else {
            adventure = new Adventure(JsonFileLoader.parseJsonFileUsingUrl(DEFAULT_JSON_FILE_URL));
        }

        adventure.getGameWorld().isValidMap(adventure.getStartingRoomName(), adventure.getEndingRoomName());
        System.out.println();

        adventure.playAdventureGame();
    }

    public Adventure(GameWorld layout) {
        gameWorld = layout;

        endingRoomName = gameWorld.getEndingRoom();
        startingRoomName = gameWorld.getStartingRoom();
        currentRoom = gameWorld.getRoomByName(startingRoomName);

        player = gameWorld.getPlayer();
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getStartingRoomName() {
        return startingRoomName;
    }

    public String getEndingRoomName() {
        return endingRoomName;
    }

    /**
     * Removes item from current room and adds it to user's inventory if item exists
     * @param userInput userInput is the user's input split into words
     * @return String message that tells user whether item was successfully taken
     */
    public String takeValidItem(String[] userInput) {

        /* Disregards first word of userInput since it is an action. Isolates item name by
           copying the rest of userInput into a subarray and joining the contents of the subarray */
        String[] itemNameAsArray = Arrays.copyOfRange(userInput, 1, userInput.length);
        String itemName = String.join(" ", itemNameAsArray);

        Item itemTaken = currentRoom.getItemByName(itemName);

        if (itemTaken != null) {

            player.addItem(itemTaken);
            currentRoom.removeItem(itemTaken);

            return "You are carrying " + itemName;
        } else {
            return "You can't take " + itemName;
        }
    }

    /**
     * Removes item from the user's inventory and adds it to the current room
     * user is in if the item exists
     * @param userInput is the user's input split into words
     * @return String message that tells user whether item was successfully dropped
     */
    public String dropValidItem(String[] userInput) {

        /* Disregards first word of userInput since it is an action. Isolates item name by
        copying the rest of userInput into a subarray and joining the contents of the subarray */
        String[] itemNameAsArray = Arrays.copyOfRange(userInput, 1, userInput.length);
        String itemName = String.join(" ", itemNameAsArray);

        Item itemDropped = player.getItemByName(itemName);

        if (itemDropped != null) {

            player.removeItem(itemDropped);
            currentRoom.addItem(itemDropped);

            return "You dropped " + itemName;
        } else {
            return "You can't drop " + itemName;
        }
    }

    /**
     * Changes room if user entered a valid direction
     * @param directionName is the direction user wants to move toward
     * @return String message that tells user whether or not they can move in that direction
     */
    public String changeRooms(String directionName) {
       Direction direction = currentRoom.getValidDirection(directionName);

        if (direction != null) {
            currentRoom = gameWorld.getRoomByName(direction.getRoom());
            return "";
        } else {
            return "You can't go " + directionName;
        }
    }

    /**
     * Gets user's input using Scanner
     * @return user's input
     */
    public String getUserInput() {
        System.out.println("What would you like to do?");
        return scan.nextLine();
    }

    /**
     * Validates user's input and performs an action based on input using helper methods
     * @param userInput is the user's input
     */
    public void usersNextMove(String userInput){

        /* Splits userInput into words to identify the user's action(go, take, etc.)
           from the object(item or direction) the action is performed on */
        String[] usersNextMove = userInput.trim().split("\\s+");

        /* Each conditional checks for an action user could take (first word of userInput)
           and checks that the object of the action (remaining words of userInput) exists
           using a helper method. Helper method will also execute action if the object is valid */
        if (usersNextMove[0].equalsIgnoreCase(TAKE_ITEM) && usersNextMove.length > 1) {
            System.out.println(takeValidItem(usersNextMove));
        } else if (usersNextMove[0].equalsIgnoreCase(DROP_ITEM) && usersNextMove.length > 1) {
            System.out.println(dropValidItem(usersNextMove));
        } else if (usersNextMove[0].equalsIgnoreCase(GO_DIRECTION) && usersNextMove.length > 1) {
            System.out.println(changeRooms(usersNextMove[1]));
        } else if (usersNextMove[0].equalsIgnoreCase(DISPLAY_ITEMS)) {
            gameWorld.getPlayer().printItemInventory();
        } else if (usersNextMove[0].equalsIgnoreCase(EXIT_GAME) || usersNextMove[0].equals(QUIT_GAME)) {
            System.exit(0);
        } else {
            System.out.println("Sorry I don't understand \'" + userInput + "\'");
        }

    }

    /**
     * Uses helper methods to run an Adventure game
     */
    public void playAdventureGame() {
        System.out.println("Your journey begins here");

        while(!currentRoom.getName().equals(endingRoomName)) {
            currentRoom.printCurrentRoom();
            usersNextMove(getUserInput());
        }

        currentRoom.printCurrentRoom();
        System.out.println("You've reached your destination. Congrats on finishing the game!");
    }
}
