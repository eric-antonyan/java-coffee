import coffee.CoffeeMachine;

public class App {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.addRandomCoffee();
        coffeeMachine.addSugarWithCoffee(5);
    }
}