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
        //Used a boolean flag variable rather than just removing item inside
        //for loop after it is located to avoid a ConcurrentModificationException
        boolean isValidItem = false;

        for (String item : items) {

            if (itemName.equalsIgnoreCase(itemName)) {
                isValidItem = true;
            }
        }

        if (isValidItem) {
            items.remove(itemName);
        }
    }

    public Direction[] getDirections() {
        return directions;
    }

}
