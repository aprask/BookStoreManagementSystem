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
    public void membershipDueDate()
    {
        System.out.println("What month did you purchase your membership? ");
        System.out.println("Type in a number 1-12 to represent the month: ");
        int membershipMonth = scanner.nextInt();
        final float premiumPayment = 7.55f;
        switch (membershipMonth) {
            case 1 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in February");
            case 2 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in March");
            case 3 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in April");
            case 4 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in May");
            case 5 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in June");
            case 6 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in July");
            case 7 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in August");
            case 8 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in September");
            case 9 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in October");
            case 10 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in November");
            case 11 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in December");
            case 12 -> System.out.println("Your membership payment of $" + premiumPayment + " will be due in January");
            default -> System.out.println("Error");
        }
    }
}
