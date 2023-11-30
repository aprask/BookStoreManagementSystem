package Store;

import Factory.Item;

import java.io.FileNotFoundException;

public interface Command
{
    public void getMenuCommand();

    /**
     *
     * @throws FileNotFoundException if file does not exist
     */
    public void makePurchaseCommand() throws FileNotFoundException;

    /**
     *
     * @param cart cart object
     * @throws FileNotFoundException if file does not exist
     */
    public void completeOrderCommand(Cart cart) throws FileNotFoundException;

    /**
     *
     * @param cart cart object
     * @throws FileNotFoundException if file does not exist
     */
    public void refundOrderCommand(Cart cart) throws FileNotFoundException;
    public void compareTwoItemsCommand();

    /**
     *
     * @param itemID given an ID remove an item
     */
    public void removeItemCommand(int itemID);

    /**
     *
     * @return if successful bank transaction
     */
    public boolean renderBankFunctionality();
    public void catalogCustomers();

    /**
     *
     * @param item given an item object
     */
    public void bagItem(Item item);
}
