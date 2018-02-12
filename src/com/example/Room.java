package com.example;


import java.util.ArrayList;
import java.util.Arrays;

public class Room {
    private String name;
    private String description;
    private ArrayList<Item> items = new ArrayList<Item>();
    private Direction[] directions;
    private ArrayList<String> monstersInRoom;
   // private boolean allMonstersDefeated;
    /** Used by the isValidMap method in the GameWorld class to check if a Room object
     * has already been visited by the method */
    private boolean visited;

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

    public ArrayList<String> getMonstersInRoom() {
        return monstersInRoom;
    }

    public String getMonster(String monsterName) {
        for (String monster: monstersInRoom) {

            if (monster.equalsIgnoreCase(monsterName)) {
                return monster;
            }
        }

        return null;
    }

    public void removeMonster(String monsterName) {
       monstersInRoom.remove(monsterName);
    }

    public boolean areAllMonstersDefeated() {
        return (monstersInRoom == null || monstersInRoom.size() <= 0);
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

    public void printMonstersInRoom() {

        if (monstersInRoom == null || monstersInRoom.size() < 1) {
            System.out.print("There are no monsters in this room");
        } else {

            System.out.print("You must battle ");

            for (int i = 0; i < monstersInRoom.size(); i++) {
                System.out.print(monstersInRoom.get(i));

                //Makes sure there is not a trailing comma after the last item is printed
                if (i < monstersInRoom.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.print(" to move further!");
        }

        System.out.println();
    }

    public void printCurrentRoom() {
        System.out.println(description);

        printItemsInRoom();
        printMonstersInRoom();

       if (areAllMonstersDefeated()) {
           printDirectionsFromRoom();
       }
    }

}
