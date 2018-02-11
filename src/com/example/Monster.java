package com.example;

import com.google.gson.annotations.SerializedName;

public class Monster {
    private static final double STATUS_INCREMENT = 5;

    private String name;
    private double attack;
    private double defense;
    @SerializedName("health")
    private double maxHealth;
    private double currentHealth;

    public String getName() {
        return name;
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

    public void initializeHealth() {
        currentHealth = maxHealth;
    }

    public void printStatus() {
        double percentOfHealthRemaining = (currentHealth / maxHealth) * 100;
        double percentOfHealthLost = 100 - percentOfHealthRemaining;

        System.out.println("Monster's health: ");

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
}
