package Store;
import Factory.Item;

import java.util.ArrayList;
public class Cart
{
    private Order order;
    protected static ArrayList<Order> orderHistory = new ArrayList<>();
    private String name;
    public Cart(Item item, String name)
    {
        order = new Order(item);
        orderHistory.add(order);
        this.name = name;
    }
    public int orderAmount() {
        return Cart.orderHistory.size();
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
        for (int i = 0; i < orderHistory.size(); i++) {
            System.out.println("****************************************");
            System.out.println("Name: " + orderHistory.get(i).getItem().getItemName());
            System.out.println("Price: $" + orderHistory.get(i).getItem().getItemPrice());
            System.out.println("Purchase ID: " + i);
            System.out.println("****************************************");
        }
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
    public void displaySoldItems()
    {
        for(int i = 0; i < orderHistory.size(); i++)
        {
            System.out.println("******************************");
            System.out.println("Name: " + orderHistory.get(i).getItem().getItemName());
            System.out.println("Price: $" + orderHistory.get(i).getItem().getItemPrice());
            System.out.println("Sold ID: " + i);
            System.out.println("******************************");
        }
    }
    public boolean restockItem(String name)
    {
        for(int i = 0; i < orderHistory.size(); i++)
        {
            if(name.equals(orderHistory.get(i).getItem().getItemName()))
            {
                orderHistory.remove(i);
                return true;
            }
        }
        return false;
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