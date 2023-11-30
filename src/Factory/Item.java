package Factory;

public abstract class Item implements ItemPlan, Comparable
{
    private String name;
    private double price;
    private boolean itemStatus;
    private String type;
    private int size;
    public Item()
    {

    }

    /**
     *
     * @param name a name
     * @param price a price
     * @param type a genre
     * @param size a length
     */
    public Item(String name, double price, String type, int size)
    {
        this.name = name;
        this.price = price;
        this.type = type;
        this.size = size;
        this.itemStatus = false;
    }

    /**
     *
     * @param itemType sets a genre
     */
    @Override
    public void setItemType(String itemType) {
        type = itemType;
    }

    /**
     *
     * @param itemName sets a name
     */
    @Override
    public void setItemName(String itemName) {
        name = itemName;
    }

    /**
     *
     * @param itemPrice sets a price
     */
    @Override
    public void setItemPrice(double itemPrice) {
        price = itemPrice;
    }

    /**
     *
     * @param status sets a SOLD status (sold = true)
     */
    @Override
    public void setItemStatus(boolean status)
    {
        itemStatus = status;
    }

    /**
     *
     * @param size sets the length
     */
    @Override
    public void setItemSize(int size)
    {
        this.size = size;
    }

    /**
     *
     * @return gets the length
     */
    public int getSize() {
        return this.size;
    }

    /**
     *
     * @return gets the genre
     */
    public String getItemType()
    {
        return this.type;
    }

    /**
     *
     * @return gets the name
     */
    public String getItemName(){
        return this.name;
    }

    /**
     *
     * @return gets the price
     */
    public double getItemPrice()
    {
        return this.price;
    }

    /**
     *
     * @param o the object to be compared.
     * @return whether the price is greater (1), less than (-1), or equal (0)
     */
    @Override
    public int compareTo(Object o) {
        if(o.getClass().equals(this.getClass()))
        {
            if (((Item) o).price < this.price)
            {
                return 1;
            }
            else if (((Item) o).price > this.price)
            {
                return -1;
            }
        }
        return 0;
    }

    /**
     *
     * @return true if sold
     */
    public boolean isStatus() {
        return itemStatus;
    }
}
