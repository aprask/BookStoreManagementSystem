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
    public Item(String name, double price, String type, int size)
    {
        this.name = name;
        this.price = price;
        this.type = type;
        this.size = size;
        this.itemStatus = false;
    }
    @Override
    public void setItemType(String itemType) {
        type = itemType;
    }

    @Override
    public void setItemName(String itemName) {
        name = itemName;
    }
    @Override
    public void setItemPrice(double itemPrice) {
        price = itemPrice;
    }
    @Override
    public void setItemStatus(boolean status)
    {
        itemStatus = status;
    }
    @Override
    public void setItemSize(int size)
    {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
    public String getItemType()
    {
        return this.type;
    }
    public String getItemName(){
        return this.name;
    }
    public double getItemPrice()
    {
        return this.price;
    }
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
    public boolean isStatus() {
        return itemStatus;
    }
}
