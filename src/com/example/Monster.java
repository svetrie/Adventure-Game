package com.example;

public class Monster {
    private static final double STATUS_INCREMENT = 5;

    private String name;
    private double attack;
    private double defense;
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

    public void setCurrentHealth(double damage) {
        if (damage > 0) {
            currentHealth -= damage;
        }
    }

    public void printStatus() {
        double percentOfHealthRemaining = (currentHealth / maxHealth) * 100;

        System.out.println("Monster's health: ");

        while(percentOfHealthRemaining >= STATUS_INCREMENT) {
            System.out.print('*');
            percentOfHealthRemaining -= STATUS_INCREMENT;
        }

        double percentOfHealthLost = 100 - percentOfHealthRemaining;

        while (percentOfHealthLost > 0) {
            System.out.print('_');
            percentOfHealthLost -= STATUS_INCREMENT;
        }
    }
}
