package Store;

import Administrator.Manager;
import Administrator.Registrar;
import Administrator.Security.Admin;
import Bank.Vault;
import Factory.DefaultCrate;
import Factory.Item;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class StandardStore implements BookStoreSpecification, Command {
    private final DefaultCrate defaultCrate = new DefaultCrate();
    private final Admin admin = new Admin();
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Float> customerWallets = new ArrayList<>();
    private static Cart cart;
    private static final Manager theManager = new Manager();
    private static final ArrayList<Integer> soldIDHistory = new ArrayList<>();
    private static boolean proceedToPurchase = false;
    private static int itemType = 0;
    public ArrayList<Float> getCustomerWallets() {
        return customerWallets;
    }

    public static int getItemType() {
        return itemType;
    }

    public static void setItemType(int itemType) {
        StandardStore.itemType = itemType;
    }
    public StandardStore() throws FileNotFoundException {
        if(unlockStore())
        {
            System.out.println("Importing default array of items.");
            System.out.println("Please wait...");
            System.out.println("\nItems:");
            defaultCrate.displayMenu();
            this.catalogCustomers();
            if(proceedToPurchase) this.makePurchaseCommand();
        }
    }
    @Override
    public void restockProduct(int itemType, int amount) {

    }

    @Override
    public double inventoryValue() {
        return this.defaultCrate.valueOfCrate();
    }

    @Override
    public void getMenuCommand() {
        defaultCrate.displayMenu();
    }

    @Override
    public void makePurchaseCommand() throws FileNotFoundException {
            int customerCount = theManager.getLineCount();
            int customerID = 0;
            while(customerCount > 0)
            {
                System.out.println(theManager.registrar.customerDetails(customerID));
                if(Registrar.customerLog.get(customerID).isPremium() && Registrar.customerLog.get(customerID) != null)
                {
                    theManager.membershipDueDate();
                }
                renderBankFunctionality();
                while(true)
                {
                    getMenuCommand();
                    itemMenu();
                    System.out.println("Wallet: $" + getCustomerWallets().get(customerID));
                    int purchaseOption = scanner.nextInt();
                    if(purchaseOption == 1 || purchaseOption == 2 || purchaseOption == 3)
                    {
                        defaultCrate.displayMenu(purchaseOption);
                        System.out.println("Wallet: $" + getCustomerWallets().get(customerID));
                        System.out.println("Purchase the desired item by its associated ID: ");
                        int desiredItem;
                        try{
                            desiredItem = scanner.nextInt();
                        } catch(InputMismatchException e)
                        {
                            System.out.println("You need to enter a number");
                            break;
                        } catch(NoSuchElementException e)
                        {
                            System.out.println("This is not a valid type");
                            break;
                        } catch(Exception e)
                        {
                            System.out.println("Error: " + e);
                            break;
                        }
                        soldIDHistory.add(desiredItem);
                        if(!(defaultCrate.retrieveSpecifiedItem(desiredItem).getItemPrice() > getCustomerWallets().get(customerID)))
                        {
                            getCustomerWallets().set(customerID, (float) (getCustomerWallets().get(customerID)-defaultCrate.retrieveSpecifiedItem(desiredItem).getItemPrice()));
                            cart = new Cart(defaultCrate.retrieveSpecifiedItem(desiredItem));
                            bagItem(defaultCrate.retrieveSpecifiedItem(desiredItem));
                        }
                        else
                        {
                            System.out.println("Insufficient Funds...");
                            break;
                        }
                    }
                    else if(purchaseOption == 4)
                    {
                        System.out.println("Cost of the inventory $" + inventoryValue());
                    }
                    else if(purchaseOption == 5)
                    {
                        compareTwoItemsCommand();
                    }
                    else if(purchaseOption == -1)
                    {
                        completeOrderCommand(cart);
                        break;
                    }

                }
                customerID++;
                customerCount--;
            }

        }

    @Override
    public void completeOrderCommand(Cart cart) throws FileNotFoundException {
        cart.orderHistory();
        System.out.println("Would you like to purchase the following items you added to your cart? (y/n)");
        String completeOrRefund = "";
        try{
            completeOrRefund = scanner.next();
        } catch(Exception e)
        {
            System.out.println("Error");
        }
        if(completeOrRefund.equalsIgnoreCase("y"))
        {
            createReceipt();
            System.out.println("Your total is: $" + cart.cartTotal());
            System.out.println("\n-------------------------------------------------------");
            System.out.println("Receipt");
            String line;
            BufferedReader bufferedReader;
            {
                try {
                    bufferedReader = new BufferedReader(new FileReader("src/Store/order.csv"));
                    while((line = bufferedReader.readLine()) != null)
                    {
                        String[] strings = line.split("\n");
                        for (String string : strings) {
                            System.out.println(string);
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("-------------------------------------------------------\n");
            {
                try {
                    bufferedReader = new BufferedReader(new FileReader("src/Store/order.csv"));
                    while((line = bufferedReader.readLine()) != null)
                    {
                        String[] strings = line.split("\n");
                        Arrays.fill(strings, "");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            cart.clearCart();
        }
        else if(completeOrRefund.equalsIgnoreCase("n"))
        {
            refundOrderCommand(cart);
        }
    }
    @Override
    public void refundOrderCommand(Cart cart) throws FileNotFoundException {
        System.out.println("Would you like to refund ALL the items (y/n)? ");
        String refundOption = "";
        try{
            refundOption = scanner.next();
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        if(refundOption.equalsIgnoreCase("y"))
        {
            System.out.println("You will be refunded: $" + cart.cartTotal());
            cart.clearCart();
        }
        else if(refundOption.equalsIgnoreCase("n"))
        {
            while(true)
            {
                cart.orderHistory();
                System.out.println("Which item by ID would you like to remove from your cart? (exit = -1) ");
                int itemByID;
                try{
                    itemByID = scanner.nextInt();
                } catch(InputMismatchException e)
                {
                    System.out.println("You need to enter a number");
                    break;
                } catch(NoSuchElementException e)
                {
                    System.out.println("This is not a valid type");
                    break;
                } catch(Exception e)
                {
                    System.out.println("Error: " + e);
                    break;
                }
                if(itemByID == -1 || cart.getOrderHistory().size() == 0)
                {
                    completeOrderCommand(cart);
                    break;
                }
                removeItemCommand(itemByID);
            }
        }
    }

    @Override
    public void compareTwoItemsCommand() {
        System.out.println("Select two items by ID to compare. ");
        System.out.println("Enter the first ID: ");
        int item1ID = 0;
        try{
            item1ID = scanner.nextInt();
        } catch(InputMismatchException e)
        {
            System.out.println("You need to enter a number");
        } catch(NoSuchElementException e)
        {
            System.out.println("This is not a valid type");
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        System.out.println("Enter the second ID: ");
        int item2ID = 0;
        try{
            item2ID = scanner.nextInt();
        } catch(InputMismatchException e)
        {
            System.out.println("You need to enter a number");
        } catch(NoSuchElementException e)
        {
            System.out.println("This is not a valid type");
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        if(item1ID > defaultCrate.getDefaultItemHistory().size() || item2ID > defaultCrate.getDefaultItemHistory().size())
        {
            if(!defaultCrate.getDefaultItemHistory().get(item1ID).isStatus() ||
                    !defaultCrate.getDefaultItemHistory().get(item2ID).isStatus())
            {
                defaultCrate.compareItemsInCrate(item1ID,item2ID);
                System.out.println();
            }
            else
            {
                System.out.println("Error. Invalid ID(s)");
                System.out.println();
            }
        }
    }

    @Override
    public void removeItemCommand(int itemID) {
        cart.removeItemFromCart(itemID);
    }
    public void bankCommands(int locationID)
    {
        switch (locationID) {
            case 1 -> System.out.println("Insert Card (Type \"1\")");
            case 2 -> System.out.println("Eject Card (Type \"2\")");
            case 3 -> System.out.println(

                    """
                    Enter Pin (Type "3")
                    Eject Card (Type "2")
                    ATM Vault Balance (Type "4")
                    """

            );
            default -> System.out.println("Not an option...");
        }
    }
    @Override
    public boolean renderBankFunctionality() {
        Vault newVault = new Vault();
        System.out.println("You need to withdraw money from our ATM before you make any purchases... ");
        System.out.println("You will need to enter your 4-digit pin number...");
        System.out.println("Awaiting Service...\n");
        System.out.println("WELCOME\n");
        bankCommands(1);
        int bankProcedure = 0;
        boolean proceedWithBank = false;
        while(!proceedWithBank)
        {
            try
            {
                bankProcedure = scanner.nextInt();
                proceedWithBank = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Error");
                System.out.println("You need to enter a number before you proceed.");
            }
        }
        while(true)
        {
            if(bankProcedure != 1)
            {
                System.out.println("YOU NEED TO INSERT A CARD BEFORE YOU DO ANYTHING ELSE!");
                bankCommands(1);
                proceedWithBank = false;
                while(!proceedWithBank)
                {
                    try
                    {
                        bankProcedure = scanner.nextInt();
                        proceedWithBank = true;
                    } catch(InputMismatchException e)
                    {
                        System.out.println("Enter a number");
                    }
                }
            }
            else {
                newVault.noCard().insertCard();
                do {
                    bankCommands(3);
                    bankProcedure = 0;
                    proceedWithBank = false;
                    while(!proceedWithBank)
                    {
                        try
                        {
                            bankProcedure = scanner.nextInt();
                            proceedWithBank = true;
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Error");
                            System.out.println("You need to enter a number before you proceed.");
                        }
                    }
                    if(bankProcedure == 3)
                    {
                        System.out.println("Type in 4-digit pin: ");
                        String attemptedPin = "";
                        proceedWithBank = false;
                        while(!proceedWithBank)
                        {
                            try
                            {
                                attemptedPin = scanner.next();
                                proceedWithBank = true;
                            }
                            catch (InputMismatchException e) {
                                System.out.println("Error");
                                System.out.println("You need to enter a number before you proceed.");
                            }
                        }
                        if(attemptedPin.length() != 4)
                        {
                            System.out.println("ERROR ");
                            newVault.hasCard().insertPin(attemptedPin);
                            break;
                        }
                        else
                        {
                            newVault.hasCard().insertPin(attemptedPin);
                            System.out.println("How much would you like to withdraw? ");
                            int withdrawnMoney = 0;
                            try
                            {
                                withdrawnMoney = scanner.nextInt();
                            }
                            catch (InputMismatchException e) {
                                System.out.println("Error");
                                System.out.println("Enter a number.");
                            }
                            if(withdrawnMoney <= newVault.getCashStoredInVault())
                            {
                                customerWallets.add((float) withdrawnMoney);
                                newVault.hasCorrectPin().withdraw(withdrawnMoney);
                                System.out.println("$" + withdrawnMoney + " added to your wallet.\n");
                                return true;
                            }
                            else
                            {
                                System.out.println("Error");
                                newVault.hasCorrectPin().withdraw(withdrawnMoney);
                            }

                        }
                    }
                    if(bankProcedure == 2)
                    {
                        newVault.hasCard().ejectCard();
                        break;
                    }
                    if(bankProcedure == 4)
                    {
                        System.out.println("Cash in Vault: " + newVault.getCashStoredInVault());
                    }
                } while (true);

            }
        }
    }
    public void itemMenu()
    {
        System.out.println("\nType \"1\" to purchase a CD");
        System.out.println("Type \"2\" to purchase a Book");
        System.out.println("Type \"3\" to purchase a DVD");
        System.out.println("Type \"4\" to display the total inventory value");
        System.out.println("Type \"5\" to compare two items");
        System.out.println("Type \"-1\" to exit/finalize order");

    }
    @Override
    public void catalogCustomers() {
        theManager.catalogCustomers();
        proceedToPurchase = true;
    }

    @Override
    public void bagItem(Item item) {
        item.setItemStatus(true);
    }
    public boolean unlockStore() {
        return admin.didPass();
    }
    public void createReceipt() throws FileNotFoundException {
        File receipt = new File("src/Store/order.csv");
        PrintWriter out = new PrintWriter(receipt);
        try {
            for(int i = 0; i < soldIDHistory.size(); i++)
            {
                out.println("-----------------------");
                out.println("Sold Item Name: " + cart.getSoldItemName(i));
                out.println(("Sold Item Price: $" + cart.getItemPrice(i)));
                out.println(("Sold Item ID: " + soldIDHistory.get(i)));
                out.println("-----------------------");
            }
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

/*
TODO: Figure out a way to separate the receipts by the customer's name
 */
