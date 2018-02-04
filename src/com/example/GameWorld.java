package com.example;

public class GameWorld {
    private String startingRoom;
    private String endingRoom;
    private Room[] rooms;

    public String getStartingRoom() {
        return startingRoom;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public Room[] getRooms() {
        return rooms;
    }
}
