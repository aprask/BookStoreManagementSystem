package Store;
public interface BookStoreSpecification
{
    /**
     *
     * @param itemType receive an itemType (1=CD,2=Book,3=DVD)
     * @param amount receive the quantity (amount = quantity, in a while loop --> amount--)
     */
    public void restockProduct(int itemType, int amount);
    public double inventoryValue();
}