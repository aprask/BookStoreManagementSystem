package Store;

import Administrator.Security.Admin;
import Factory.Book;
import Factory.Crate;
import Factory.Engineer;
import java.util.Scanner;

public class BookStore implements BookStoreSpecification, Command {
    private static final Crate crate = new Crate();
    private final Admin admin = new Admin();
    private Scanner scanner = new Scanner(System.in);
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
        System.out.println("\nHere are the available items: \n");
        crate.openCrate();
    }

    /**
     * @param itemType receive an itemType (1=CD,2=Book,3=DVD)
     * @param amount   receive the quantity (amount = quantity, in a while loop --> amount--)
     */
    @Override
    public void restockProduct(int itemType, int amount) {

    }
    @Override
    public double inventoryValue() {
        return 0;
    }
    @Override
    public void getMenuCommand() {

    }
    @Override
    public void makePurchaseCommand() {

    }
    @Override
    public void completeOrderCommand() {

    }
    @Override
    public void refundOrderCommand() {

    }
    @Override
    public void compareTwoItemsCommand() {

    }
    @Override
    public void removeItemCommand() {

    }
    @Override
    public void restockItemCommand() {

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
