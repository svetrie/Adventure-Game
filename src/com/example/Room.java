package com.example;

import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private ArrayList<String> items = new ArrayList<String>();
    private Direction[] directions;

    /** Used by the isValidMap method in the GameWorld class to check if a Room object
     * has already been visited by the method */
    private boolean visited;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void addItem(String itemName) {
        items.add(itemName);
    }

    public void removeItem(String itemName) {

        if (items.contains(itemName)) {
            items.remove(itemName);
        }
    }

    public Direction[] getDirections() {
        return directions;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
