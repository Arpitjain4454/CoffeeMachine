package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int money;
    private Map<String, Coffee> menu;

    public CoffeeMachine() {
        water = 0;
        milk = 0;
        beans = 0;
        money = 0;
        menu = new HashMap<>();
        menu.put("espresso", new Coffee("espresso", 4, 250, 0, 16));
        menu.put("latte", new Coffee("latte", 7, 350, 75, 20));
        menu.put("cappuccino", new Coffee("cappuccino", 6, 200, 100, 12));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("What would you like to do? (buy, fill, take, show, analytics, exit)");
            String action = scanner.next();
            switch (action) {
                case "buy":
                    buyCoffee(scanner);
                    break;
                case "fill":
                    fillIngredients(scanner);
                    break;
                case "take":
                    takeMoney();
                    break;
                case "show":
                    showIngredients();
                    break;
                case "analytics":
                    showAnalytics();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid action, please try again.");
                    break;
            }
        }
    }

    private void buyCoffee(Scanner scanner) {
        System.out.println("Which coffee would you like to buy? (espresso, latte, cappuccino)");
        String coffeeType = scanner.next();
        Coffee coffee = menu.get(coffeeType);
        if (coffee == null) {
            System.out.println("Invalid coffee type, please try again.");
            return;
        }
        if (water < coffee.getWater()) {
            System.out.println("Sorry, not enough water.");
            return;
        }
        if (milk < coffee.getMilk()) {
            System.out.println("Sorry, not enough milk.");
            return;
        }
        if (beans < coffee.getBeans()) {
            System.out.println("Sorry, not enough beans.");
            return;
        }
        System.out.println("Dispensing coffee: " + coffeeType);
        water -= coffee.getWater();
        milk -= coffee.getMilk();
        beans -= coffee.getBeans();
        money += coffee.getPrice();
        coffee.incrementCount();
    }

    private void fillIngredients(Scanner scanner) {
        System.out.println("How much water would you like to add? (in ml)");
        int addedWater = scanner.nextInt();
        System.out.println("How much milk would you like to add? (in ml)");
        int addedMilk = scanner.nextInt();
        System.out.println("How many beans would you like to add? (in grams)");
        int addedBeans = scanner.nextInt();
        water += addedWater;
        milk += addedMilk;
        beans += addedBeans;
    }

    private void takeMoney() {
        System.out.println("Taking out money: $" + money);
        money = 0;
    }

    private void showIngredients() {
        System.out.println("Current ingredient levels:");
        System.out.println("Water: " + water + " ml");
        System.out.println("Milk: " + milk + " ml");
        System.out.println("Beans: " + beans + " g");
        System.out.println("Money: $" + money);
    }

    private void showAnalytics() {
        int totalSold = 0;
        int totalEarned = 0;
        int totalWater = 0;
        int totalMilk = 0;
        int totalBeans = 0;
        for (Coffee coffee : menu.values()) {
            int count = coffee.getCount();
            totalSold += count;
            totalEarned += count * coffee.getPrice();
            totalWater += count * coffee.getWater();
            totalMilk += count * coffee.getMilk();
            totalBeans += count * coffee.getBeans();
            System.out.println(coffee.getType() + " sold: " + count);
        }
        System.out.println("Total coffees sold: " + totalSold);
        System.out.println("Total money earned: $" + totalEarned);
        System.out.println("Total water consumed: " + totalWater + " ml");
        System.out.println("Total milk consumed: " + totalMilk + " ml");
        System.out.println("Total beans consumed: " + totalBeans + " g");
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.run();
    }
}

class Coffee {
    private String type;
    private int price;
    private int water;
    private int milk;
    private int beans;
    private int count;

    public Coffee(String type, int price, int water, int milk, int beans) {
        this.type = type;
        this.price = price;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.count = 0;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }
}
