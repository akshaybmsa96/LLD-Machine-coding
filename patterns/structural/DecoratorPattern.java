package patterns.structural;

public class DecoratorPattern {
    public static void main(String[] args) {

        Coffee mycoffee = new SimpleCoffee();
        mycoffee = new CoffeeBoba(mycoffee);
        mycoffee = new CoffeeMatcha(mycoffee);
        mycoffee = new CoffeeWhipCream(mycoffee);

        System.out.println("Coffee description: " + mycoffee.description() + " cost: " + mycoffee.cost());

    }
}

/**
 * When you need to add functionality on run time, you use decorator pattern
 */
interface Coffee {
    int cost();
    String description();
}

class SimpleCoffee implements Coffee{

    public int cost() {
        return 100;
    }

    public String description() {
        return "Simple coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }

}

class CoffeeWhipCream extends CoffeeDecorator{

    public CoffeeWhipCream(Coffee coffee) {
        super(coffee);
    }

    public int cost() {
        return coffee.cost()+10;
    }

    public String description() {
        return coffee.description() + " Added Whip cream ";
    }
}

class CoffeeBoba extends CoffeeDecorator{

    public CoffeeBoba(Coffee coffee) {
        super(coffee);
    }

    public int cost() {
        return coffee.cost()+20;
    }

    public String description() {
        return coffee.description() + " Added Boba";
    }
}

class CoffeeMatcha extends CoffeeDecorator{

    public CoffeeMatcha(Coffee coffee) {
        super(coffee);
    }

    public int cost() {
        return coffee.cost()+30;
    }

    public String description() {
        return coffee.description() + " Added Matcha";
    }
}
