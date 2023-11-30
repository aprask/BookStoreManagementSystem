package Store;

import Administrator.Manager;
import Administrator.Registrar;
import Administrator.Security.Admin;
import Administrator.Security.NameException;
import Bank.Vault;
import Factory.Custom.CustomCrate;
import Factory.Item;

import java.io.*;
import java.util.*;
public class CustomStore implements BookStoreSpecification, Command {
    private static final CustomCrate crate = new CustomCrate();
    private final Admin admin = new Admin();
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Float> customerWallets = new ArrayList<>();
    private static Cart cart;
    private static final Manager theManager = new Manager();
    private static final ArrayList<Integer> soldIDHistory = new ArrayList<>();
    private static boolean proceedToPurchase = false;
    private Account account;

    /**
     *
     * @throws FileNotFoundException if file not found
     */
    public CustomStore() throws FileNotFoundException {
        if(unlockStore())
        {
            System.out.println("You need to build the items before the store opens...\n");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while(true)
            {
                System.out.println("What type of item would you like to create (1=CD,2=Book,3=DVD,-1=Exit)? ");
                int itemType;
                try {
                    itemType = scanner.nextInt();
                    crate.addToBuildHistory(itemType);
                } catch(InputMismatchException e)
                {
                    System.out.println("You need to enter a number");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                    System.out.println("We will assign you a CD to build...");
                    itemType = 1;
                    crate.addToBuildHistory(itemType);
                    break;
                } catch(NoSuchElementException e)
                {
                    System.out.println("This is not a valid type");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                    System.out.println("We will assign you a Book to build...");
                    itemType = 2;
                    crate.addToBuildHistory(itemType);
                    break;
                } catch(Exception e)
                {
                    System.out.println("Error: " + e);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                    System.out.println("We will assign you a DVD to build...");
                    itemType = 3;
                    crate.addToBuildHistory(itemType);
                    break;
                }
                if(itemType == -1)
                {
                    break;
                }
            }
            this.catalogCustomers();
            if(proceedToPurchase) this.makePurchaseCommand();
        }

    }

    /**
     *
     * @return inventory total
     */
    @Override
    public double inventoryValue() {
        return crate.valueOfCrate();
    }
    @Override
    public void getMenuCommand() {
        crate.openCrate();
    }

    /**
     *
     * @throws FileNotFoundException if file not found
     */
    @Override
    public void makePurchaseCommand() throws FileNotFoundException {
        int customerCount = theManager.getLineCount();
        int currentCustomerID = 0;
        while(customerCount > 0)
        {
            System.out.println(theManager.registrar.customerDetails(currentCustomerID));
            String currentCustomer = theManager.registrar.getCustomerNameByID(currentCustomerID);
            account = new Account(currentCustomer, currentCustomerID);
            if(Registrar.customerLog.get(currentCustomerID).isPremium() && Registrar.customerLog.get(currentCustomerID) != null)
            {
                theManager.membershipDueDate();
            }
            renderBankFunctionality();
            while(true)
            {
                getMenuCommand();
                itemMenu();
                System.out.println("Wallet: $" + getCustomerWallets().get(currentCustomerID));
                int purchaseOption = scanner.nextInt();
                if(purchaseOption == 1 || purchaseOption == 2 || purchaseOption == 3)
                {
                    crate.openCrate(purchaseOption);
                    System.out.println("Wallet: $" + getCustomerWallets().get(currentCustomerID));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                    System.out.println("Purchase the desired item by its associated ID: ");
                    int desiredItem;
                    try{
                        desiredItem = scanner.nextInt();
                    } catch(InputMismatchException e)
                    {
                        System.out.println("You need to enter a number");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException o) {
                            throw new RuntimeException(o);
                        }
                        break;
                    } catch(NoSuchElementException e)
                    {
                        System.out.println("This is not a valid type");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException o) {
                            throw new RuntimeException(o);
                        }
                        break;
                    } catch(Exception e)
                    {
                        System.out.println("Error: " + e);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException o) {
                            throw new RuntimeException(o);
                        }
                        break;
                    }
                    soldIDHistory.add(desiredItem);
                    if(!(crate.retrieveSpecifiedItem(desiredItem).getItemPrice() > getCustomerWallets().get(currentCustomerID)))
                    {
                        getCustomerWallets().set(currentCustomerID, (float) (getCustomerWallets().get(currentCustomerID)-crate.retrieveSpecifiedItem(desiredItem).getItemPrice()));
                        cart = new Cart(crate.retrieveSpecifiedItem(desiredItem), null);
                        bagItem(crate.retrieveSpecifiedItem(desiredItem));
                    }
                    else
                    {
                        System.out.println("Insufficient Funds...");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException o) {
                            throw new RuntimeException(o);
                        }
                        break;
                    }
                }
                else if(purchaseOption == 4)
                {
                    System.out.println("Cost of the inventory $" + inventoryValue());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
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
            currentCustomerID++;
            customerCount--;
        }

    }

    /**
     *
     * @param cart cart object
     */
    @Override
    public void completeOrderCommand(Cart cart) throws FileNotFoundException {
        System.out.println("\n\tReceipt: ");
        cart.orderHistory();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
        System.out.println("Would you like to purchase the following items you added to your cart? (y/n)");
        String completeOrRefund = "";
        try{
            completeOrRefund = scanner.next();
        } catch(Exception e)
        {
            System.out.println("Error");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
        }
        if(completeOrRefund.equalsIgnoreCase("y"))
        {
            try {
                addToLedger(account);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Your total is: $" + cart.cartTotal());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            postStore();
        }
        else if(completeOrRefund.equalsIgnoreCase("n"))
        {
            refundOrderCommand(cart);
        }
    }

    /**
     *
     * @param cart cart object
     */
    @Override
    public void refundOrderCommand(Cart cart) throws FileNotFoundException {
        System.out.println("Would you like to refund ALL the items (y/n)? ");
        String refundOption = "";
        try{
            refundOption = scanner.next();
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
        }
        if(refundOption.equalsIgnoreCase("y"))
        {
            System.out.println("You will be refunded: $" + cart.cartTotal());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
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
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                    break;
                } catch(NoSuchElementException e)
                {
                    System.out.println("This is not a valid type");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                    break;
                } catch(Exception e)
                {
                    System.out.println("Error: " + e);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                    break;
                }
                if(itemByID == -1 || cart.getOrderHistory().size() == 0)
                {
                    completeOrderCommand(cart);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                    break;
                }
                removeItemCommand(itemByID);
            }
            postStore();
        }
    }
    @Override
    public void compareTwoItemsCommand() {
        System.out.println("Select two items by ID to compare. ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enter the first ID: ");
        int item1ID = 0;
        try{
            item1ID = scanner.nextInt();
        } catch(InputMismatchException e)
        {
            System.out.println("You need to enter a number");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
        } catch(NoSuchElementException e)
        {
            System.out.println("This is not a valid type");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
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
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
        } catch(NoSuchElementException e)
        {
            System.out.println("This is not a valid type");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        crate.compareItemsInCrate(item1ID,item2ID);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }

    /**
     *
     * @param itemID given an ID remove an item
     */
    @Override
    public void removeItemCommand(int itemID) {
        cart.removeItemFromCart(itemID);
    }

    /**
     *
     * @return true if transaction is successful
     */
    @Override
    public boolean renderBankFunctionality() {
        Vault newVault = new Vault();
        System.out.println("You need to withdraw money from our ATM before you make any purchases... ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
        System.out.println("You will need to enter your 4-digit pin number...");
        System.out.println("Awaiting Service...\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
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
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException o) {
                    throw new RuntimeException(o);
                }
                System.out.println("You need to enter a number before you proceed.");
            }
        }
        while(true)
        {
            if(bankProcedure != 1)
            {
                System.out.println("YOU NEED TO INSERT A CARD BEFORE YOU DO ANYTHING ELSE!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException o) {
                    throw new RuntimeException(o);
                }
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
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException o) {
                            throw new RuntimeException(o);
                        }
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
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException o) {
                                throw new RuntimeException(o);
                            }
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
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException o) {
                                    throw new RuntimeException(o);
                                }
                                System.out.println("You need to enter a number before you proceed.");
                            }
                        }
                        if(attemptedPin.length() != 4)
                        {
                            System.out.println("ERROR ");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException o) {
                                throw new RuntimeException(o);
                            }
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
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException o) {
                                    throw new RuntimeException(o);
                                }
                                System.out.println("Enter a number.");
                            }
                            if(withdrawnMoney <= newVault.getCashStoredInVault())
                            {
                                customerWallets.add((float) withdrawnMoney);
                                newVault.hasCorrectPin().withdraw(withdrawnMoney);
                                System.out.println("$" + withdrawnMoney + " added to your wallet.\n");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException o) {
                                    throw new RuntimeException(o);
                                }
                                return true;
                            }
                            else
                            {
                                System.out.println("Error");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException o) {
                                    throw new RuntimeException(o);
                                }
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
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException o) {
                            throw new RuntimeException(o);
                        }
                    }
                } while (true);

            }
        }
    }


    @Override
    public void catalogCustomers() {
        theManager.catalogCustomers();
        proceedToPurchase = true;
    }

    /**
     *
     * @param item given an item object
     */
    @Override
    public void bagItem(Item item) {
        item.setItemStatus(true);
    }

    /**
     *
     * @param locationID given the state of the vault by the ID
     */
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
    public void itemMenu()
    {
        System.out.println("\nType \"1\" to purchase a CD");
        System.out.println("Type \"2\" to purchase a Book");
        System.out.println("Type \"3\" to purchase a DVD");
        System.out.println("Type \"4\" to display the total inventory value");
        System.out.println("Type \"5\" to compare two items");
        System.out.println("Type \"-1\" to exit/finalize order");

    }

    /**
     *
     * @return if the store is unlocked
     */
    public boolean unlockStore() {
        return admin.didPass();
    }

    /**
     *
     * @return get the customer's wallet
     */
    public ArrayList<Float> getCustomerWallets() {
        return customerWallets;
    }

    /**
     *
     * @param account after each purchase, add the order to the .csv ledger file
     */
    public void addToLedger(Account account) throws IOException {
        String receipt = "src/Store/ledger.csv";
        FileWriter writer = new FileWriter("src/Main/Misc/sales_report.html", true);
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter((receipt),true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            // to the CSV file
            out.write("**********************************\n");
            out.write("Customer Name: " + account.getName() + "\n");
            out.write("Customer ID: " + account.getID() + "\n");
            for(int i = 0; i < account.customerOrderHistory.size(); i++)
            {
                // to the CSV file
                out.write("---------------------------------\n");
                out.write("Item Name: " + cart.getSoldItemName(i) + "\n");
                out.write("Item Price: $" + cart.getItemPrice(i) + "\n");
                out.write("Date: " + java.time.LocalDate.now() + "\n");
                out.write("---------------------------------\n");

                // to the HTML file
                writer.write("<p>Date: " + java.time.LocalDate.now() + ": </p>\n");
                writer.write("<p>Name: " + cart.getSoldItemName(i) + "</p>\n");
                writer.write("<p>Price: $" + cart.getItemPrice(i) + "</p>\n");
                writer.write("<hr />\n");
            }
            out.write("**********************************\n");
            out.close();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void postStore()
    {
        System.out.println("\nWould you like to restock the inventory for the next order? ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
        System.out.println("\t\"1\" = Yes, \"2\" = No");
        int restockOption = 0;
        try{
            restockOption = scanner.nextInt();
        } catch(Exception e)
        {
            System.out.println("Error: " + e);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
        }
        scanner.nextLine();
        if(restockOption == 1)
        {
            while(true)
            {
                cart.displaySoldItems();
                if(Cart.orderHistory.size() == 0)
                {
                    cart.clearCart();
                    break;
                }
                System.out.println("Select an item by its << NAME >> to restock (\"exit\" = Exit)");
                String selectByName = scanner.nextLine();
                try{
                    checkProductName(selectByName);
                } catch (NameException e) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                    throw new RuntimeException(e);
                } catch (Exception e)
                {
                    System.out.println("Error: " + e);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                }
                if(selectByName.equalsIgnoreCase("exit"))
                {
                    System.out.println("Bye");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException o) {
                        throw new RuntimeException(o);
                    }
                    break;
                }
                else
                {
                    if(cart.restockItem(selectByName))
                    {
                        crate.retrieveSpecifiedItem(selectByName).setItemStatus(false);
                        System.out.println("Restock Successful");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException o) {
                            throw new RuntimeException(o);
                        }
                    }
                    else
                    {
                        System.out.println("Name DNE");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException o) {
                            throw new RuntimeException(o);
                        }
                    }
                }
            }
            cart.clearCart();
        }
        else
        {
            cart.clearCart();
        }
    }
    public static void checkProductName(String name) throws NameException
    {
        for(char c: name.toCharArray())
        {
            if(Character.isDigit(c))
            {
                throw new NameException("\nProduct names cannot contain digits.");
            }
        }
    }
}