package com.example;


import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private String name;
    private String description;
    private ArrayList<Item> items = new ArrayList<Item>();
    //private HashMap<String,Item> itemNameMap = new HashMap<>();
    private Direction[] directions;
    //private HashMap<String, Direction> directionNameMap;
    private String[] monstersInRoom;


    /** Used by the isValidMap method in the GameWorld class to check if a Room object
     * has already been visited by the method */
    private boolean visited;
/*
    public Room() {

        for (Item item : items) {
            itemNameMap.put(item.getName(), item);
        }

        for (Direction direction : directions) {
            directionNameMap.put(direction.getDirectionName(), direction);
        }

        visited = false;
    }
*/

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItemByName(String itemName) {
        for (Item item : items) {

            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }

        return null;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Direction[] getDirections() {
        return directions;
    }

    /**
     * Checks if the direction user entered is a valid direction to move toward from this room
     * @param directionName is the direction user entered in
     * @return Direction object of this room whose name matches directionName or null if
     * no such Direction object exits
     */
    public Direction getValidDirection(String directionName) {

        for (Direction direction : directions) {

            if (direction.getDirectionName().equalsIgnoreCase(directionName)) {
                return direction;
            }
        }

        return null;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String[] getMonstersInRoom() {
        return monstersInRoom;
    }

    public void printItemsInRoom() {
        System.out.print("This room contains ");

        if (items == null || items.size() < 1) {
            System.out.print("nothing");
        } else {

            for (int i = 0; i < items.size(); i++) {
                System.out.print(items.get(i).getName());

                //Makes sure there is not a trailing comma after the last item is printed
                if (i < items.size() - 1) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println();
    }

    public void printDirectionsFromRoom() {
        System.out.println("From here you can go: ");

        for (Direction direction : directions) {
            System.out.println("\t" + direction.getDirectionName() + " to " + direction.getRoom());
        }
    }

    public void printCurrentRoom() {
        System.out.println(description);

        printItemsInRoom();
        printDirectionsFromRoom();
    }

}
