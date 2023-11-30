package Store;

import java.util.ArrayList;

public class Account
{
    private String name;
    private final int ID;
    protected final ArrayList<Integer> customerOrderHistory = new ArrayList<>();

    /**
     *
     * @param name user's name
     * @param ID user's ID
     */
    public Account(String name, int ID)
    {
        this.name = name;
        this.ID = ID;
    }

    /**
     *
     * @param orderNum add order ID to history of purchased items
     */
    public void addToOrderHistory(int orderNum)
    {
        this.customerOrderHistory.add(orderNum);
    }

    /**
     *
     * @return get a name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name set a name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return get an ID
     */
    public int getID() {
        return ID;
    }
}
