package Factory;

public abstract class Item implements ItemPlan, Comparable
{
    private String name;
    private double price;
    private int ID = 0;
    private boolean status;
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
        this.status = false;
        this.ID++;
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
    public void setItemStatus()
    {
        status = false;
    }
    @Override
    public void setItemID()
    {
        ID++;
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
}
