package com.example;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Player {
    private static final int DUEL_EXP_MULTIPLIER = 20;
    private static final double NEXT_LEVEL_EXPERIENCE_MULTIPLIER = 1.1;
    private static final double LEVEL1_EXP = 25;
    private static final double LEVEL2_EXP = 50;
    private static final double ATTACK_MULTIPLIER = 1.5;
    private static final double DEFENSE_MULTIPLIER = 1.5;
    private static final double HEALTH_MULTIPLIER = 1.3;
    private static final int STATUS_INCREMENT = 5;

    private String name;
    private ArrayList<Item> itemInventory = new ArrayList<Item>();
    private double attack;
    private double defense;
    @SerializedName("health")
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

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void reduceCurrentHealth(double damage) {
        if (damage > 0) {
            currentHealth -= damage;
        }
    }

    public void initializeCurrentHealth() {
        currentHealth = maxHealth;
    }

    /**
     * Prints player's health percentage in a progress bar format.
     * Prints a '*' for each 5% increment of health the player has and
     * a '-' for each 5% of health lost
     */
    public void printStatus() {
        double percentOfHealthRemaining = (currentHealth / maxHealth) * 100;
        double percentOfHealthLost = 100 - percentOfHealthRemaining;

        System.out.println("Player's health: ");

        while(percentOfHealthRemaining >= STATUS_INCREMENT) {
            System.out.print('*');
            percentOfHealthRemaining -= STATUS_INCREMENT;
        }

        while (percentOfHealthLost > 0) {
            System.out.print('-');
            percentOfHealthLost -= STATUS_INCREMENT;
        }

        System.out.println();
    }

    public double getTotalExp() {
        return totalExp;
    }

    public double getExpForNextLevel() {
        return expForNextLevel;
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

    public String getPlayerInfo() {
        return ("Level: " + level + ", Attack: " + attack + ", Defense: "
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

    /**
     * Once a player defeats a monster, this method uses monster's stats to give player
     * experience points.
     * Player levels up if experience points surpass experience needed for
     * next level.
     * @param monster is the monster the player has defeated
     */
    public void gainExperience(Monster monster) {
         totalExp += ((monster.getAttack() + monster.getDefense())/2
                + monster.getMaxHealth()) * DUEL_EXP_MULTIPLIER;

        if (level == 0 && totalExp >= LEVEL1_EXP) {
            levelUp();

            expForNextLevel = LEVEL2_EXP;
            lastLevelExp = LEVEL1_EXP;
        }

        /* Not an if/else statement because it is possible for player to level up immediately
        from 0 to 2 after defeating one monster */
        if (level >= 1) {

            /*Possible for player to repeatedly level up if they gain large amount of experience */
            while(totalExp >= expForNextLevel) {
                levelUp();
            }
        }
    }

    /**
     * Increases player's level and stats and calculates experience needed for next level.
     * Also, restores player's health fully
     */
    public void levelUp() {
        level ++;

        double tempExp = expForNextLevel;
        expForNextLevel = (expForNextLevel + lastLevelExp) * NEXT_LEVEL_EXPERIENCE_MULTIPLIER;
        lastLevelExp = tempExp;

        attack *= ATTACK_MULTIPLIER;
        defense *= DEFENSE_MULTIPLIER;
        maxHealth *= HEALTH_MULTIPLIER;

        currentHealth = maxHealth;
    }
}
