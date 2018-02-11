package com.example;

import java.util.ArrayList;
import java.util.HashMap;

public class GameWorld {
    private String startingRoom;
    private String endingRoom;
    private HashMap<String,Room> nameToRoom = new HashMap<>();
    private Room[] rooms;
    private Player player;
    private ArrayList<Monster> monsters;

    public void initializeHealth() {
        for (Monster monster : monsters) {
            monster.initializeHealth();
        }

        player.initializeHealth();
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
        for (Room room : rooms) {

            if (room.getName().equalsIgnoreCase(roomName)) {
                return room;
            }
        }

        return null;
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

    /*public void removeMonster(Monster monster) {
        monsters.remove(monster);
    }*/

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
