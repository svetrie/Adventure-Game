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

    public Room getRoomByName(String roomName) {
        for (Room room : rooms) {

            if (room.getName().equals(roomName)) {
                return room;
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
