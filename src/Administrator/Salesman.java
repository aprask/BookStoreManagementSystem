package Administrator;
import Administrator.Security.AgeException;
import Administrator.Security.MembershipTypeException;
import Administrator.Security.NameException;

import java.util.*;
public class Salesman implements Pitch {
    private final Scanner scanner = new Scanner(System.in);
    protected static int amountOfCustomers;
    private final String[] paymentMethods = {"AMX", "Mastercard", "Discover", "Visa"};
    @Override
    public int lineTotal() {
        try{
            System.out.println("How many customers are in the line? ");
            amountOfCustomers = scanner.nextInt();
        } catch(InputMismatchException e)
        {
            System.out.println("Error: " + e);
            System.out.println("You need to enter a whole number");
            amountOfCustomers = scanner.nextInt();
        } catch(NoSuchElementException e)
        {
            System.out.println("Error: " + e);
            System.out.println("This is not a valid type");
            amountOfCustomers = scanner.nextInt();
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
            amountOfCustomers = scanner.nextInt();
        }
        return amountOfCustomers;
    }
    @Override
    public String askForName() {
        System.out.println("What is your name? ");
        String name = "";
        try{
            name = scanner.next();
            checkCustomerName(name);
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        return name;
    }
    @Override
    public int askForAge() {
        System.out.println("Age? ");
        int age = 0;
        try
        {
            age = scanner.nextInt();
            checkCustomerAge(age);
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        return age;
    }
    @Override
    public String askForPaymentType() {
        do {
            System.out.println("Payment type? (Choose from the following)");
            System.out.println("\nAMX = 1\nMastercard = 2\nDiscover = 3\nVisa = 4");
            String selectPaymentType = scanner.next();
            switch (selectPaymentType) {
                case "1" -> {
                    return this.paymentMethods[0];
                }
                case "2" -> {
                    return this.paymentMethods[1];
                }
                case "3" -> {
                    return this.paymentMethods[2];
                }
                case "4" -> {
                    return this.paymentMethods[3];
                }
                default -> System.out.println("Error");
            }
        } while (true);
    }
    @Override
    public String askForPremium() {
        System.out.println("Would you like premium membership (y/n)? ");
        String memberType = "";
        try{
            memberType = scanner.next();
            checkMembershipType(memberType);
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        return memberType;
    }
    public void membershipDueDate()
    {
        System.out.println("What month did you purchase your premium membership? ");
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
        System.out.println("\n");
    }
    public static void checkCustomerAge(int age) throws AgeException{
        if(age < 16)
        {
            throw new AgeException("\nYou must be 16 to purchase at this store.");
        }
    }
    public static void checkCustomerName(String name) throws NameException{
        for(char c: name.toCharArray())
        {
            if(Character.isDigit(c))
            {
                throw new NameException("\nNames cannot contain digits.");
            }
        }
    }
    public static void checkMembershipType(String type) throws MembershipTypeException{
        if(!type.equalsIgnoreCase("y") || !type.equalsIgnoreCase("n"))
        {
            throw new MembershipTypeException("\nThere are only two choices, \"y\" and \"n\"...");
        }
    }
}