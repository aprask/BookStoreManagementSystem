package Store;
import Factory.Item;

import java.util.ArrayList;
public class Cart
{
    private Order order;
    protected static ArrayList<Order> orderHistory = new ArrayList<>();
    protected static ArrayList<Integer> orderAmountHistory = new ArrayList<>();
    private String name;
    public Cart(Item item, String name)
    {
        order = new Order(item);
        orderHistory.add(order);
        this.name = name;
    }
    public int getCartAmount()
    {
        return orderAmountHistory.size();
    }
    public double cartTotal()
    {
        double total = 0;
        for (Order value : orderHistory) {
            total += value.item.getItemPrice();
        }
        return total;
    }
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
        int amount = 0;
        for (Order value : orderHistory) {
            System.out.println("****************************************");
            System.out.println("Name: " + value.getItem().getItemName());
            System.out.println("Price: $" + value.getItem().getItemPrice());
            System.out.println("****************************************");
            amount++;
        }
        orderAmountHistory.add(amount);
    }
    public String getSoldItemName(int ID)
    {
        for(int i = 0; i < orderHistory.size(); i++)
        {
            if(ID == i)
            {
                return orderHistory.get(i).getItem().getItemName();
            }

        }
        return null;
    }
    public double getItemPrice(int ID)
    {
        for(int i = 0; i < orderHistory.size(); i++)
        {
            if(ID == i)
            {
                return orderHistory.get(i).getItem().getItemPrice();
            }
        }
        return -1;
    }
    public void clearCart(){
        orderHistory.clear();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        Cart.orderHistory = orderHistory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static class Order {
        private Item item;
        public Order(Item item)
        {
            this.item = item;
        }
        public Item getItem() {
            return item;
        }
        public void setItem(Item item) {
            this.item = item;
        }
    }
}