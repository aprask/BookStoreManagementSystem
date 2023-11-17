package Store;

import Administrator.Security.Admin;

public class BookStore implements BookStoreSpecification, Command {
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
    @Override
    public boolean unlockStore(Admin admin) {
        return admin.didPass();
    }
}
