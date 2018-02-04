package com.example;

import java.util.Scanner;

public class Adventure {

    private static final String DEFAULT_JSON_FILE_URL = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
    private static final Scanner scan = new Scanner(System.in);

    private GameWorld gameWorld;
    private String startingRoomName;
    private String endingRoomName;
    private String currentRoomName;
    private Room currentRoom;

    public static void main(String[] args) {
        Adventure a = new Adventure();
        a.loadGameWorld();
        a.printCurrentRoom();
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
        currentRoomName = startingRoomName;
        currentRoom = gameWorld.getRoomByName(currentRoomName);
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
    }

    public void printDirectionsFromRoom() {
        System.out.println("From here you can go: ");

        for (Direction direction : currentRoom.getDirections()) {
            System.out.println(direction.getDirectionName() + " to " + direction.getRoom());
        }
    }

    public void printCurrentRoom() {
        System.out.println(currentRoom.getDescription());

        if(currentRoomName.equals(endingRoomName)) {
            System.out.println("You've reached your destination");
            System.out.println("Congrats on completing the game!");
            System.exit(0);
        } else {

            if(currentRoomName.equals(startingRoomName)) {
                System.out.println("Your journey begins here");
            }

            printItemsInRoom();
            printDirectionsFromRoom();
        }
    }


    /* uses the JsonFileLoader class to generate a new GameWorld object
       initialize startingRoom, endingRoom, and set currentRoom = to starting room
    */
}
