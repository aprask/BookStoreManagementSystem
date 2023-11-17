package Store;

import Factory.Item;

import java.util.ArrayList;

public class Cart
{
    private Order order;
    private ArrayList<Order> orderHistory = new ArrayList<>();
    public Cart(Item item)
    {
        order = new Order(item);
        this.orderHistory.add(order);
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
        this.orderHistory = orderHistory;
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
