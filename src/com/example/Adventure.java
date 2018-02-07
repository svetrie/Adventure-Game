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

    private GameWorld gameWorld;
    private String startingRoomName;
    private String endingRoomName;
    private ArrayList<String> itemInventory;
    private Room currentRoom;

    public static void main(String[] args) {
        Adventure adventure;

        if (args.length > 0) {
            adventure = new Adventure(JsonFileLoader.parseJsonFileUsingFilePath(args[0]));
        } else {
            adventure = new Adventure(JsonFileLoader.parseJsonFileUsingUrl(DEFAULT_JSON_FILE_URL));
        }

        adventure.playAdventureGame();
    }

    public String getStartingRoomName() {
        return startingRoomName;
    }

    public String getEndingRoomName() {
        return endingRoomName;
    }

    public ArrayList<String> getItemInventory() {
        return itemInventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Adventure(GameWorld layout) {
        gameWorld = layout;

        endingRoomName = gameWorld.getEndingRoom();
        startingRoomName = gameWorld.getStartingRoom();
        currentRoom = gameWorld.getRoomByName(startingRoomName);
        itemInventory = new ArrayList<String>();
    }

    public void printItemsInRoom() {
        System.out.print("This room contains ");

        if (currentRoom.getItems().size() < 1) {
            System.out.print("nothing");
        } else {

            for (int i = 0; currentRoom.getItems() != null && i < currentRoom.getItems().size(); i++) {
                System.out.print(currentRoom.getItems().get(i));

                //Makes sure there is not a trailing comma after the last item is printed
                if (i < currentRoom.getItems().size() - 1) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println();
    }

    public void printDirectionsFromRoom() {
        System.out.println("From here you can go: ");

        for (Direction direction : currentRoom.getDirections()) {
            System.out.println("\t" + direction.getDirectionName() + " to " + direction.getRoom());
        }
    }

    public void printCurrentRoom() {
        System.out.println(currentRoom.getDescription());

        printItemsInRoom();
        printDirectionsFromRoom();
    }

    public void printItemInventory() {
        System.out.print("You are carrying ");

        if (itemInventory.size() < 1) {
            System.out.print("nothing");
        } else {

            for (int i = 0; i < itemInventory.size(); i++) {
                System.out.print(itemInventory.get(i));

                //Makes sure there is not a trailing comma after the last item is printed
                if (i < itemInventory.size() - 1) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println();
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

        if (currentRoom.getItems().contains(itemName)) {

            itemInventory.add(itemName);
            currentRoom.removeItem(itemName);

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

        if (itemInventory.contains(itemName)) {

            itemInventory.remove(itemName);
            currentRoom.addItem(itemName);

            return "You dropped " + itemName;

        } else {
            return "You can't drop " + itemName;
        }
    }

    /**
     * Checks if the direction user entered is a valid direction to move toward from the
     * current room's location
     * @param directionName is the direction user wants to move in
     * @return Direction object of current room whose name matches directionName or null if
     * no such Direction object exits
     */
    public Direction getValidDirection(String directionName) {

        for (Direction direction : currentRoom.getDirections()) {

            if (direction.getDirectionName().equalsIgnoreCase(directionName)) {
                return direction;
            }
        }

        return null;
    }

    /**
     * Changes room if user entered a valid direction
     * @param directionName is the direction user wants to move toward
     * @return String message that tells user whether or not they can move in that direction
     */
    public String changeRooms(String directionName) {
       Direction direction = getValidDirection(directionName);

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
            printItemInventory();
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
            printCurrentRoom();
            usersNextMove(getUserInput());
        }

        printCurrentRoom();
        System.out.println("You've reached your destination. Congrats on finishing the game!");
    }
}
