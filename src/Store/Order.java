package Store;

public class Order
{
    private int numberOfCustomers;
    public Order(int numberOfCustomers)
    {
        this.numberOfCustomers = numberOfCustomers;
    }
    public void initializeOrder()
    {

    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }
}
