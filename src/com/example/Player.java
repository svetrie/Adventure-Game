package com.example;

import java.util.ArrayList;

public class Player {
    private static final int DUEL_EXP_MULTIPLIER = 20;
    private static final double NEXT_LEVEL_EXPERIENCE_MULTIPLIER = 1.1;
    private static final double LEVEL1_EXP = 25;
    private static final double LEVEL2_EXP = 50;
    private static final int LEVEL2_CUTOFF = 2;
    private static final double ATTACK_MULTIPLIER = 1.5;
    private static final double DEFENSE_MULTIPLIER = 1.5;
    private static final double HEALTH_MULTIPLIER = 1.3;

    private String name;
    private ArrayList<Item> itemInventory = new ArrayList<Item>();
    private double attack;
    private double defense;
    private double maxHealth;
    private double currentHealth;

    private int level;
    private double totalExp;
    private double expForNextLevel;
    private double lastLevelExp;

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

    public double getCurrentHealth() {
        return currentHealth;
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

    public void printPlayerInfo() {
        System.out.println("Level: " + level + ", Attack: " + attack + ", Defense: "
                + defense + ", Health: " + currentHealth + "/" + maxHealth);
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

    public void gainExperience(Monster monster) {
         totalExp += ((monster.getAttack() + monster.getDefense())/2
                + monster.getMaxHealth()) * DUEL_EXP_MULTIPLIER;

        if (level == 1 && totalExp >= LEVEL1_EXP) {
            levelUp();

            expForNextLevel = LEVEL2_EXP;
            lastLevelExp = LEVEL1_EXP;
            //Should currentExp reset back to 0?
            //currentExp = LEVEL1_EXP - currentExp;
        } else if (level >= 2 && totalExp >= expForNextLevel) {
            levelUp();
        }
    }

    public void levelUp() {
        level ++;

        if (level > LEVEL2_CUTOFF) {
            double tempExp = expForNextLevel;

            expForNextLevel = (expForNextLevel + lastLevelExp) * NEXT_LEVEL_EXPERIENCE_MULTIPLIER;

            lastLevelExp = tempExp;
        }

        attack *= ATTACK_MULTIPLIER;
        defense *= DEFENSE_MULTIPLIER;
        maxHealth *= HEALTH_MULTIPLIER;

        currentHealth = maxHealth;
    }

}
