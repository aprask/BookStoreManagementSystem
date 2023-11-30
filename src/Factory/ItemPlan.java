package Factory;

public interface ItemPlan
{
    /**
     *
     * @param itemType set the genre
     */
    public void setItemType(String itemType);

    /**
     *
     * @param itemName set the name
     */
    public void setItemName(String itemName);

    /**
     *
     * @param itemPrice set the price
     */
    public void setItemPrice(double itemPrice);

    /**
     *
     * @param status set the sold status
     */
    public void setItemStatus(boolean status);

    /**
     *
     * @param size set the length
     */
    public void setItemSize(int size);
}
