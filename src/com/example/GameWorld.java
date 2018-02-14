package com.example;

import java.util.ArrayList;
import java.util.HashMap;

public class GameWorld {
    private String startingRoom;
    private String endingRoom;
    private Room[] rooms;
    private HashMap<String,Room> roomMap = new HashMap<>();
    private Player player;
    private ArrayList<Monster> monsters;

    public void initializeRoomMap(){
        for (Room room : rooms) {
            roomMap.put(room.getName(), room);
        }
    }

    public String getStartingRoom() {
        return startingRoom;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public Room getRoomByName(String roomName) {
        return roomMap.get(roomName);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public Monster getMonsterByName(String monsterName) {
        for (Monster monster : monsters) {

            if (monster.getName().equalsIgnoreCase(monsterName)) {
                return monster;
            }
        }

        return null;
    }

    public void isValidMap(String currentRoomName, String endingRoomName) {

        Room currentRoom = getRoomByName(currentRoomName);
        currentRoom.setVisited(true);

        if (currentRoomName.equals(endingRoomName)) {
            System.out.println("The layout is valid. A path exists between these two rooms");
        }

        for (Direction direction : currentRoom.getDirections()) {

            if (!getRoomByName(direction.getRoom()).getVisited()) {
                isValidMap(direction.getRoom(), endingRoomName);
            }
        }
    }

}
