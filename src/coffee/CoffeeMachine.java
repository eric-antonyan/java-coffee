package coffee;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CoffeeMachine {
    private final Lock coffeeLock = new ReentrantLock();
    private final Lock sugarLock = new ReentrantLock();
    private static final int ONE_SPOON = 50;
    private final Random random = new Random();

    public void addRandomCoffee() {
        coffeeLock.lock();
        try {
            int coffeeAmount = random.nextInt(100) + 1;
            System.out.println("Pouring " + coffeeAmount + " grams of coffee...");
            Thread.sleep(1000);
            System.out.println("Coffee amount poured: " + coffeeAmount + " grams.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            coffeeLock.unlock();
        }
    }

    public void addSugarWithCoffee(int spoonCount) {
        sugarLock.lock();
        try {
            int sugarGrams = 0;

            for (int i = 0; i < spoonCount; i++) {
                sugarGrams += ONE_SPOON;
                System.out.println("Adding 1 spoon of sugar (50 grams), total sugar now: " + sugarGrams + " grams.");
                
                Thread coffeeThread = new Thread(this::addRandomCoffee);
                coffeeThread.start();

                Thread.sleep(500);
            }

            System.out.println("Total sugar added: " + sugarGrams + " grams.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sugarLock.unlock();
        }
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();

        Thread sugarThread = new Thread(() -> machine.addSugarWithCoffee(5));  // Adding 5 spoons of sugar

        sugarThread.start();
    }
}
