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
        Adventure a = new Adventure();
        a.loadGameWorld();
        a.playAdventureGame();
    }

    public void loadGameWorld() {
        System.out.println("Hello welcome to the Adventure Game!");
        System.out.println("Please enter the JSON file path that contains the map you want to play from");
        System.out.println("(Press ENTER twice if you would like to use the default map)");
        String jsonFile = scan.nextLine();

        if (jsonFile.length() > 0) {
            gameWorld = JsonFileLoader.parseJsonFileUsingFilePath();
        } else {
            gameWorld = JsonFileLoader.parseDefaultJsonFileUsingUrl(DEFAULT_JSON_FILE_URL);
        }

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

            for (int i = 0; i < currentRoom.getItems().size(); i++) {
                System.out.print(currentRoom.getItems().get(i));

                if (i < currentRoom.getItems().size() - 1) {
                    System.out.print(" ,");
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

        if(currentRoom.getName().equals(endingRoomName)) {
            System.out.println("You've reached your destination");
            System.out.println("Congrats on completing the game!");
            System.exit(0);
        } else {

            if(currentRoom.getName().equals(startingRoomName)) {
                System.out.println("Your journey begins here");
            }

            printItemsInRoom();
            printDirectionsFromRoom();
        }
    }

    public void addValidItem(String itemName) {
        boolean isValidItem = false;

        for (String item : currentRoom.getItems()) {

            if (item.equals(itemName)) {
                itemInventory.add(item);
                isValidItem = true;
            }
        }

        if (isValidItem) {
            currentRoom.removeItem(itemName);
            System.out.println("You are carrying " + itemName);
        } else {
            System.out.println("You can't take " + itemName);
        }
    }

    public void dropValidItem(String itemName) {
        boolean isValidItem = false;

        for (String item : itemInventory) {

            if (item.equals(itemName)) {
                itemInventory.remove(item);
                isValidItem = true;
            }
        }

        if (isValidItem) {
            System.out.println("You dropped " + itemName);
        } else {
            System.out.println("You can't drop " + itemName);
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

    public void changeRooms(String directionName) {
       Direction direction = getValidDirection(directionName);

        if (direction != null) {
            currentRoom = gameWorld.getRoomByName(direction.getRoom());
        } else {
            System.out.print("I can't go " + directionName);
        }
    }

    public void usersNextMove(){
        System.out.println("What would you like to do?");
        String userInput = scan.nextLine();
        String[] usersNextMove = userInput.toLowerCase().split("\\s+");

        if (usersNextMove[0].equals(TAKE_ITEM) && usersNextMove.length > 1) {
            addValidItem(usersNextMove[1]);
        } else if (usersNextMove[0].equals(DROP_ITEM) && usersNextMove.length > 1) {
            dropValidItem(usersNextMove[0]);
        } else if (usersNextMove[0].equals(GO_DIRECTION) && usersNextMove.length > 1) {
            changeRooms(usersNextMove[1]);
        } else if (usersNextMove[0].equals(DISPLAY_ITEMS)) {
            printItemsInRoom();
        } else if (usersNextMove[0].equals(EXIT_GAME) || usersNextMove[0].equals(QUIT_GAME)) {
            System.exit(0);
        } else {
            System.out.println("Sorry I don't understand \'" + userInput + "\'");
        }
    }

    public void playAdventureGame() {
        while(!currentRoom.getName().equals(endingRoomName)) {
            printCurrentRoom();
            usersNextMove();
        }
    }
}
