package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

    private static final String DEFAULT_JSON_FILE_URL = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
    private static final Scanner scan = new Scanner(System.in);

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
        Adventure adventure = new Adventure();

        if (args.length > 0) {
            adventure.createGameWorld(JsonFileLoader.parseJsonFileUsingFilePath(args[1]));
        } else {
            adventure.createGameWorld(JsonFileLoader.parseJsonFileUsingUrl(DEFAULT_JSON_FILE_URL));
        }

        adventure.playAdventureGame();
    }

    public GameWorld getGameWorld() {
        return gameWorld;
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

    public void createGameWorld(GameWorld layout) {
        gameWorld = layout;

        endingRoomName = gameWorld.getEndingRoom();
        startingRoomName = gameWorld.getStartingRoom();
        currentRoom = gameWorld.getRoomByName(startingRoomName);
        itemInventory = new ArrayList<String>();
    }

    public void printItemsInRoom() {
        System.out.print("This room contains ");

        if (currentRoom.getItems() != null && currentRoom.getItems().size() < 1) {
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

    public String takeValidItem(String itemName) {

        if (currentRoom.getItems() != null && currentRoom.getItems().contains(itemName)) {

            itemInventory.add(itemName);
            currentRoom.removeItem(itemName);

            return "You are carrying " + itemName;

        } else {
            return "You can't take " + itemName;
        }
    }

    public String dropValidItem(String itemName) {

        if (itemInventory != null && itemInventory.contains(itemName)) {

            itemInventory.remove(itemName);
            currentRoom.addItem(itemName);

            return "You dropped " + itemName;

        } else {
            return "You can't drop " + itemName;
        }
    }

    public Direction getValidDirection(String directionName) {

        for (Direction direction : currentRoom.getDirections()) {

            if (direction.getDirectionName().toLowerCase().equals(directionName)) {
                return direction;
            }
        }

        return null;
    }

    public String changeRooms(String directionName) {
       Direction direction = getValidDirection(directionName);

        if (direction != null) {
            currentRoom = gameWorld.getRoomByName(direction.getRoom());
            return null;
        } else {
            return "You can't go " + directionName;
        }

    }

    public String getUserInput() {
        System.out.println("What would you like to do?");
        return scan.nextLine();
    }

    public void usersNextMove(String userInput){
        String[] usersNextMove = userInput.trim().toLowerCase().split("\\s+");

        if (usersNextMove[0].equals(TAKE_ITEM) && usersNextMove.length > 1) {
            System.out.println(takeValidItem(usersNextMove[1]));
        } else if (usersNextMove[0].equals(DROP_ITEM) && usersNextMove.length > 1) {
            System.out.println(dropValidItem(usersNextMove[1]));
        } else if (usersNextMove[0].equals(GO_DIRECTION) && usersNextMove.length > 1) {
            if(changeRooms(usersNextMove[1]) != null) {
                System.out.println(changeRooms(usersNextMove[1]));
            }
        } else if (usersNextMove[0].equals(DISPLAY_ITEMS)) {
            printItemInventory();
        } else if (usersNextMove[0].equals(EXIT_GAME) || usersNextMove[0].equals(QUIT_GAME)) {
            System.exit(0);
        } else {
            System.out.println("Sorry I don't understand \'" + userInput + "\'");
        }

    }

    public void playAdventureGame() {
        System.out.println("Your journey begins here");

        while(!currentRoom.getName().equals(endingRoomName)) {
            printCurrentRoom();
            usersNextMove(getUserInput());
        }

        System.out.println("You've reached your destination. Congrats on finishing the game!");
    }
}
