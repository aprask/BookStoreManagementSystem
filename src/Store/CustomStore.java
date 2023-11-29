package Store;

import Administrator.Manager;
import Administrator.Registrar;
import Administrator.Security.Admin;
import Bank.Vault;
import Factory.Crate;
import Factory.Item;

import java.io.*;
import java.util.*;
public class CustomStore implements BookStoreSpecification, Command {
    private static final Crate crate = new Crate();
    private final Admin admin = new Admin();
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Float> customerWallets = new ArrayList<>();
    private static Cart cart;
    private static final Manager theManager = new Manager();
    private static final ArrayList<Integer> soldIDHistory = new ArrayList<>();
    private static boolean proceedToPurchase = false;
    private static int itemType = 0;
    private Account account;
    public CustomStore() throws FileNotFoundException {
        if(unlockStore())
        {
            System.out.println("You need to build the items before the store opens...\n");
            while(true)
            {
                System.out.println("What type of item would you like to create (1=CD,2=Book,3=DVD,-1=Exit)? ");
                try {
                    itemType = scanner.nextInt();
                    crate.addToBuildHistory(itemType);
                } catch(InputMismatchException e)
                {
                    System.out.println("You need to enter a number");
                    System.out.println("We will assign you a CD to build...");
                    itemType = 1;
                    crate.addToBuildHistory(itemType);
                    break;
                } catch(NoSuchElementException e)
                {
                    System.out.println("This is not a valid type");
                    System.out.println("We will assign you a Book to build...");
                    itemType = 2;
                    crate.addToBuildHistory(itemType);
                    break;
                } catch(Exception e)
                {
                    System.out.println("Error: " + e);
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
    public static int getItemType() {
        return itemType;
    }

    public static void setItemType(int itemType) {
        CustomStore.itemType = itemType;
    }

    /**
     * @param itemType receive an itemType (1=CD,2=Book,3=DVD)
     * @param amount   receive the quantity (amount = quantity, in a while loop --> amount--)
     */
    @Override
    public void restockProduct(int itemType, int amount) {
        while(amount > 0){
            crate.addToBuildHistory(itemType);
            amount--;
        }
    }
    @Override
    public double inventoryValue() {
        return crate.valueOfCrate();
    }
    @Override
    public void getMenuCommand() {
        crate.openCrate();
    }
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
                    if(!(crate.retrieveSpecifiedItem(desiredItem).getItemPrice() > getCustomerWallets().get(currentCustomerID)))
                    {
                        getCustomerWallets().set(currentCustomerID, (float) (getCustomerWallets().get(currentCustomerID)-crate.retrieveSpecifiedItem(desiredItem).getItemPrice()));
                        cart = new Cart(crate.retrieveSpecifiedItem(desiredItem), null);
                        bagItem(crate.retrieveSpecifiedItem(desiredItem));
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
            currentCustomerID++;
            customerCount--;
        }

    }
    @Override
    public void completeOrderCommand(Cart cart) throws FileNotFoundException {
        System.out.println("\n\tReceipt: ");
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
            addToLedger(account);
            System.out.println("Your total is: $" + cart.cartTotal());
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
                int itemByID = 0;
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
        System.out.println();
        crate.compareItemsInCrate(item1ID,item2ID);
        System.out.println();
    }
    @Override
    public void removeItemCommand(int itemID) {
        cart.removeItemFromCart(itemID);
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


    @Override
    public void catalogCustomers() {
        theManager.catalogCustomers();
        proceedToPurchase = true;
    }

    @Override
    public void bagItem(Item item) {
        item.setItemStatus(true);
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
    public void itemMenu()
    {
        System.out.println("\nType \"1\" to purchase a CD");
        System.out.println("Type \"2\" to purchase a Book");
        System.out.println("Type \"3\" to purchase a DVD");
        System.out.println("Type \"4\" to display the total inventory value");
        System.out.println("Type \"5\" to compare two items");
        System.out.println("Type \"-1\" to exit/finalize order");

    }
    public boolean unlockStore() {
        return admin.didPass();
    }


    public ArrayList<Float> getCustomerWallets() {
        return customerWallets;
    }

    public void addToLedger(Account account) {
        String receipt = "src/Store/ledger.csv";
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter((receipt),true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out.write("**********************************\n");
            out.write("Customer Name: " + account.getName() + "\n");
            out.write("Customer ID: " + account.getID() + "\n");
            for(int i = 0; i < account.customerOrderHistory.size(); i++)
            {
                out.write("---------------------------------\n");
                out.write("Item Name: " + cart.getSoldItemName(i) + "\n");
                out.write("Item Price: $" + cart.getItemPrice(i) + "\n");
                out.write("Date: " + java.time.LocalDate.now() + "\n");
                out.write("---------------------------------\n");
            }
            out.write("**********************************\n");
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
