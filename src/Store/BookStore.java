package Store;

import Administrator.Security.Admin;
import Bank.Vault;
import Factory.Crate;
import java.util.Scanner;

public class BookStore implements BookStoreSpecification, Command {
    private static final Crate crate = new Crate();
    private final Admin admin = new Admin();
    private Scanner scanner = new Scanner(System.in);
    private Vault vault; // TODO add bank features
    private Cart cart;
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
        }
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
        while(true)
        {
            getMenuCommand();
            System.out.println("\nType \"1\" to purchase a CD");
            System.out.println("Type \"2\" to purchase a Book");
            System.out.println("Type \"3\" to purchase a DVD");
            System.out.println("Type \"4\" to display the total inventory value");
            System.out.println("Type \"5\" to compare two items");
            System.out.println("Type \"-1\" to exit/finalize order");
            int purchaseOption = scanner.nextInt();
            if(purchaseOption == 1 || purchaseOption == 2 || purchaseOption == 3)
            {
                crate.openCrate(purchaseOption);
                System.out.println("Purchase the desired item by its associated ID: ");
                int desiredItem = scanner.nextInt();
                cart = new Cart(crate.retrieveSpecifiedItem(desiredItem));
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
    }
    @Override
    public void completeOrderCommand(Cart cart) {
        cart.orderHistory();
        System.out.println("Would you like to purchase the following items you added to your cart? (y/n)");
        String completeOrRefund = scanner.next();
        if(completeOrRefund.equalsIgnoreCase("y"))
        {
            System.out.println("Your total is: $" + cart.cartTotal());
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
    public boolean unlockStore() {
        return admin.didPass();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
