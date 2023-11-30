package Administrator;
import Administrator.Security.AgeException;
import Administrator.Security.MembershipTypeException;
import Administrator.Security.NameException;

import java.util.*;
public class Salesman implements Pitch {
    private final Scanner scanner = new Scanner(System.in);
    protected static int amountOfCustomers;
    private final String[] PAYMENT_METHODS = {"AMX", "Mastercard", "Discover", "Visa"};
    private static String memberType;
    private static String memberName;
    private static int memberAge;
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

    /**
     *
     * @return return a name
     */
    @Override
    public String askForName() {
        System.out.println("What is your name? ");
        memberName = "";
        try{
            memberName = scanner.next();
            checkCustomerName(memberName);
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
            memberName = "Jeffrey";
        }
        return memberName;
    }

    /**
     *
     * @return return an age
     */
    @Override
    public int askForAge() {
        System.out.println("Age? ");
        memberAge = 0;
        try
        {
            memberAge = scanner.nextInt();
            checkCustomerAge(memberAge);
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
            memberAge = 16;
        }
        return memberAge;
    }

    /**
     *
     * @return return a payment type
     */
    @Override
    public String askForPaymentType() {
        do {
            System.out.println("Payment type? (Choose from the following)");
            System.out.println("\nAMX = 1\nMastercard = 2\nDiscover = 3\nVisa = 4");
            String selectPaymentType = scanner.next();
            switch (selectPaymentType) {
                case "1" -> {
                    return this.PAYMENT_METHODS[0];
                }
                case "2" -> {
                    return this.PAYMENT_METHODS[1];
                }
                case "3" -> {
                    return this.PAYMENT_METHODS[2];
                }
                case "4" -> {
                    return this.PAYMENT_METHODS[3];
                }
                default -> System.out.println("Error");
            }
        } while (true);
    }

    /**
     *
     * @return return a y/n based on the premium status
     */
    @Override
    public String askForPremium() {
        System.out.println("Would you like premium membership (y/n)? ");
        memberType = "";
        try{
            memberType = scanner.next();
            checkMembershipType(memberType);
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
            memberType = "n";
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

    /**
     *
     * @param age given an age
     * @throws AgeException determine whether it is less than 16
     */
    public static void checkCustomerAge(int age) throws AgeException{
        if(age < 16)
        {
            memberAge = 16;
            throw new AgeException("\nYou must be 16 to purchase at this store.\n...We will assume you are 16");
        }
    }

    /**
     *
     * @param name given a name
     * @throws NameException determine if it contains digits
     */
    public static void checkCustomerName(String name) throws NameException{
        for(char c: name.toCharArray())
        {
            if(Character.isDigit(c))
            {
                memberName = "Jeffrey";
                throw new NameException("\nNames cannot contain digits.\nYou will be known as Jeffrey");
            }
        }
    }

    /**
     *
     * @param type given a membership y/n
     * @throws MembershipTypeException determine if the membership is a VALID option (something other than y/n)
     */
    public static void checkMembershipType(String type) throws MembershipTypeException{
        if(type.equalsIgnoreCase("y") || type.equalsIgnoreCase("n"))
        {
            System.out.println("Wise choice.");
        }
        else {
            memberType = "n";
            throw new MembershipTypeException("\nYou do not have a valid membership type\nYou will be given a standard membership");
        }
    }
}