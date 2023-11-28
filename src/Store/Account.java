package Store;

import java.util.ArrayList;

public class Account
{
    private String name;
    private int ID;
    protected final ArrayList<Integer> customerOrderHistory = new ArrayList<>();
    public Account(String name, int ID)
    {
        this.name = name;
        this.ID = ID;
    }
    public void addToOrderHistory(int orderNum)
    {
        this.customerOrderHistory.add(orderNum);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
