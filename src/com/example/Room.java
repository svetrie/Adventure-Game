package com.example;

import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private ArrayList<String> items;
    private Direction[] directions;

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
        for (String item : items) {

            if (itemName.equalsIgnoreCase(itemName)) {
                items.remove(item);
            }
        }
    }

    public Direction[] getDirections() {
        return directions;
    }

}
