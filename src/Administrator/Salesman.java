package Administrator;
import java.util.*;

public class Salesman implements Pitch {
    private final Scanner scanner = new Scanner(System.in);
    private static int amountOfCustomers;

    public static int getAmountOfCustomers() {
        return amountOfCustomers;
    }

    public static void setAmountOfCustomers(int amountOfCustomers) {
        Salesman.amountOfCustomers = amountOfCustomers;
    }

    @Override
    public int lineTotal() {
        System.out.println("How many customers are in the line? ");
        amountOfCustomers = scanner.nextInt();
        return amountOfCustomers;
    }

    @Override
    public String askForName() {
        System.out.println("What is your name? ");
        return scanner.next();
    }
    @Override
    public int askForAge() {
        System.out.println("Age? ");
        return scanner.nextInt();
    }

    @Override
    public double askForBalance() {
        System.out.println("Bank balance? ");
        return scanner.nextDouble();
    }

    @Override
    public String askForPaymentType() {
        System.out.println("Payment type? ");
        return scanner.next();
    }

    @Override
    public String askForPremium() {
        System.out.println("Would you like premium membership (y/n)? ");
        return scanner.next();
    }
}
