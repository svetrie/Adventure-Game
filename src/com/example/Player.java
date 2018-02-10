package com.example;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Item> itemInventory = new ArrayList<Item>();
    private double attack;
    private double defense;
    private double health;
    private int level;

    public String getName() {
        return name;
    }

    public ArrayList<Item> getItemInventory() {
        return itemInventory;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public Item getItemByName(String itemName) {
        for (Item item : itemInventory) {

            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }

        return null;
    }

    public void addItem(Item item) {
        itemInventory.add(item);
    }

    public void removeItem(Item item) {
        itemInventory.remove(item);
    }

    public void printItemInventory() {
        System.out.print("You are carrying ");

        if (itemInventory.size() < 1) {
            System.out.print("nothing");
        } else {

            for (int i = 0; i < itemInventory.size(); i++) {
                System.out.print(itemInventory.get(i).getName());

                //Makes sure there is not a trailing comma after the last item is printed
                if (i < itemInventory.size() - 1) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println();
    }

}
