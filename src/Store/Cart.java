package Store;
import Factory.Item;
import java.util.ArrayList;
public class Cart
{
    private Order order;
    private static ArrayList<Order> orderHistory = new ArrayList<>();
    public Cart(Item item)
    {
        order = new Order(item);
        orderHistory.add(order);
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
        for (Order value : orderHistory) {
            System.out.println("****************************************");
            System.out.println("Name: " + value.getItem().getItemName());
            System.out.println("Price: $" + value.getItem().getItemPrice());
            System.out.println("****************************************");
        }
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