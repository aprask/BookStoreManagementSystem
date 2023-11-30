package Store;
import Factory.Item;

import java.util.ArrayList;
public class Cart
{
    protected static ArrayList<Order> orderHistory = new ArrayList<>();
    private String name;

    /**
     *
     * @param item item object
     * @param name string name
     */
    public Cart(Item item, String name)
    {
        Order order = new Order(item);
        orderHistory.add(order);
        this.name = name;
    }

    /**
     *
     * @return the size of the cart
     */
    public int orderAmount() {
        return Cart.orderHistory.size();
    }

    /**
     *
     * @return the cart total in cost
     */
    public double cartTotal()
    {
        double total = 0;
        for (Order value : orderHistory) {
            total += value.item.getItemPrice();
        }
        return total;
    }

    /**
     *
     * @param itemID removes an item from the cart by ID
     */
    public void removeItemFromCart(int itemID)
    {
        for(int i = 0; i < orderHistory.size(); i++)
        {
            if(i == itemID)
            {
                orderHistory.remove(i);
            }
        }
    }
    public void orderHistory()
    {
        for (int i = 0; i < orderHistory.size(); i++) {
            System.out.println("****************************************");
            System.out.println("Name: " + orderHistory.get(i).item().getItemName());
            System.out.println("Price: $" + orderHistory.get(i).item().getItemPrice());
            System.out.println("Purchase ID: " + i);
            System.out.println("****************************************");
        }
    }

    /**
     *
     * @param ID given an ID
     * @return return the sold item's name (admixture function w/ crate)
     */
    public String getSoldItemName(int ID)
    {
        for(int i = 0; i < orderHistory.size(); i++)
        {
            if(ID == i)
            {
                return orderHistory.get(i).item().getItemName();
            }

        }
        return null;
    }

    /**
     *
     * @param ID given an ID
     * @return get the price of the item
     */
    public double getItemPrice(int ID)
    {
        for(int i = 0; i < orderHistory.size(); i++)
        {
            if(ID == i)
            {
                return orderHistory.get(i).item().getItemPrice();
            }
        }
        return -1;
    }
    public void clearCart(){
        orderHistory.clear();
    }

    /**
     *
     * @return order history list
     */
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    /**
     *
     * @return get the order name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name set the order name
     */
    public void setName(String name) {
        this.name = name;
    }
    public void displaySoldItems()
    {
        for(int i = 0; i < orderHistory.size(); i++)
        {
            System.out.println("******************************");
            System.out.println("Name: " + orderHistory.get(i).item().getItemName());
            System.out.println("Price: $" + orderHistory.get(i).item().getItemPrice());
            System.out.println("Sold ID: " + i);
            System.out.println("******************************");
        }
    }

    /**
     *
     * @param name given a name of an item
     * @return if the item exists (restock) otherwise return false
     */
    public boolean restockItem(String name)
    {
        for(int i = 0; i < orderHistory.size(); i++)
        {
            if(name.equals(orderHistory.get(i).item().getItemName()))
            {
                orderHistory.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     *
     * @param item the order record storing instances of item objects
     */
    private record Order(Item item) { }
}