package Store;

import Factory.Item;

import java.io.FileNotFoundException;

public interface Command
{
    public void getMenuCommand();
    public void makePurchaseCommand() throws FileNotFoundException;
    public void completeOrderCommand(Cart cart) throws FileNotFoundException;
    public void refundOrderCommand(Cart cart) throws FileNotFoundException;
    public void compareTwoItemsCommand();
    public void removeItemCommand(int itemID);
    public boolean renderBankFunctionality();
    public void catalogCustomers();
    public void bagItem(Item item);
}
