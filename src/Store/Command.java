package Store;

import Administrator.Security.Admin;

public interface Command
{
    public void getMenuCommand();
    public void makePurchaseCommand();
    public void completeOrderCommand(Cart cart);
    public void refundOrderCommand(Cart cart);
    public void compareTwoItemsCommand();
    public void removeItemCommand(int itemID);
}
