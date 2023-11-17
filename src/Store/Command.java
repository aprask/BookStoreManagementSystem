package Store;

import Administrator.Security.Admin;

public interface Command
{
    public void getMenuCommand();
    public void makePurchaseCommand();
    public void completeOrderCommand();
    public void refundOrderCommand();
    public void compareTwoItemsCommand();
    public void removeItemCommand();
    public void restockItemCommand();
}
