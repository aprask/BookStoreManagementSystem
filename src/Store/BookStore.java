package Store;

import Administrator.Manager;
import Administrator.Security.Admin;
import Bank.Vault;
import Factory.Crate;
import Factory.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class BookStore implements BookStoreSpecification, Command {
    private static final Crate crate = new Crate();
    private final Admin admin = new Admin();
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Float> customerWallets = new ArrayList<>();
    private static Cart cart;
    private static final Manager theManager = new Manager();
    private static boolean proceedToPurchase = false;
    public BookStore()
    {
        if(unlockStore())
        {
            System.out.println("You need to build the items before the store opens...\n");
            while(true)
            {
                System.out.println("What type of item would you like to create (1=CD,2=Book,3=DVD,-1=Exit)? ");
                int itemType = scanner.nextInt();
                if(itemType == -1)
                {
                    break;
                }
                crate.addToBuildHistory(itemType);
            }
            this.catalogCustomers();
            if(proceedToPurchase) this.makePurchaseCommand();
        }

    }

    public static void setProceedToPurchase(boolean proceedToPurchase) {
        BookStore.proceedToPurchase = proceedToPurchase;
    }
    public Cart getCart() {
        return BookStore.cart;
    }
    public void setCart(Cart cart) {
        BookStore.cart = cart;
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
    public void makePurchaseCommand() {
        int customerCount = theManager.getLineCount();
        int customerID = 0;
        while(customerCount > 0)
        {
            System.out.println("Hello, " + theManager.registrar.customerDetails(customerID));
            renderBankFunctionality();
            while(true)
            {
                getMenuCommand();
                System.out.println("\nType \"1\" to purchase a CD");
                System.out.println("Type \"2\" to purchase a Book");
                System.out.println("Type \"3\" to purchase a DVD");
                System.out.println("Type \"4\" to display the total inventory value");
                System.out.println("Type \"5\" to compare two items");
                System.out.println("Type \"-1\" to exit/finalize order");
                System.out.println("Wallet: $" + getCustomerWallets().get(customerID));
                int purchaseOption = scanner.nextInt();
                if(purchaseOption == 1 || purchaseOption == 2 || purchaseOption == 3)
                {
                    crate.openCrate(purchaseOption);
                    System.out.println("Wallet: $" + getCustomerWallets().get(customerID));
                    System.out.println("Purchase the desired item by its associated ID: ");
                    int desiredItem = scanner.nextInt();
                    if(!(crate.retrieveSpecifiedItem(desiredItem).getItemPrice() > getCustomerWallets().get(customerID)))
                    {
                        getCustomerWallets().set(customerID, (float) (getCustomerWallets().get(customerID)-crate.retrieveSpecifiedItem(desiredItem).getItemPrice()));
                        cart = new Cart(crate.retrieveSpecifiedItem(desiredItem));
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
            customerID++;
            customerCount--;
        }

    }
    @Override
    public void completeOrderCommand(Cart cart) {
        cart.orderHistory();
        System.out.println("Would you like to purchase the following items you added to your cart? (y/n)");
        String completeOrRefund = scanner.next();
        if(completeOrRefund.equalsIgnoreCase("y"))
        {
            System.out.println("Your total is: $" + cart.cartTotal());
            cart.clearCart();
        }
        else if(completeOrRefund.equalsIgnoreCase("n"))
        {
            refundOrderCommand(cart);
        }
    }
    @Override
    public void refundOrderCommand(Cart cart) {
        System.out.println("Would you like to refund ALL the items (y/n)? ");
        String refundOption = scanner.next();
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
                int itemByID = scanner.nextInt();
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
        System.out.println("Select two items by ID to compare: ");
        int item1ID = scanner.nextInt();
        int item2ID = scanner.nextInt();
        crate.compareItemsInCrate(item1ID,item2ID);
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
        System.out.println("Awaiting Service...");
        System.out.println("WELCOME");
        bankCommands();
        int bankProcedure = scanner.nextInt();
        while(true)
        {
            if(bankProcedure != 1)
            {
                System.out.println("YOU NEED TO INSERT A CARD BEFORE YOU DO ANYTHING ELSE!");
            }
            else {
                newVault.noCard().insertCard();
                bankCommands();
                bankProcedure = scanner.nextInt();
                if(bankProcedure == 3)
                {
                    System.out.println("Type in 4-digit pin: ");
                    String attemptedPin = scanner.next();
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
                        int withdrawnMoney = scanner.nextInt();
                        if(withdrawnMoney <= newVault.getCashStoredInVault())
                        {
                            customerWallets.add((float) withdrawnMoney);
                            newVault.hasCorrectPin().withdraw(withdrawnMoney);
                            System.out.println("$" + withdrawnMoney + " added to your wallet.\n");
                            break;
                        }
                        else
                        {
                            System.out.println("Error");
                            newVault.hasCorrectPin().withdraw(withdrawnMoney);
                        }

                    }
                }
                else if(bankProcedure == 2)
                {
                    System.out.println("Ejection Procedure Initiated...");
                    newVault.hasCard().ejectCard();
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public void catalogCustomers() {
        theManager.catalogCustomers();
        proceedToPurchase = true;
    }

    @Override
    public void bagItem(Item item) {
        item.setItemStatus(true); // TODO fix (we need to bag the items/make the disappear after they are purchased)
    }

    public void bankCommands()
    {
        System.out.println("1 == Insert Card\n2 == Eject Card\n3 == Enter Pin\n4 == Check Vault's Balance: \n");
    }
    public boolean unlockStore() {
        return admin.didPass();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public ArrayList<Float> getCustomerWallets() {
        return customerWallets;
    }

    public void setCustomerWallets(ArrayList<Float> customerWallets) {
        this.customerWallets = customerWallets;
    }
}
