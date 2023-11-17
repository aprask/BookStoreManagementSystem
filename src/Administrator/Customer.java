package Administrator;

public class Customer extends Person
{
    private boolean premium;
    private int ID = 0;

    public Customer(String name, int age, double balance, String paymentType, boolean premium) {
        super(name, age, balance, paymentType);
        this.premium = premium;
        ID++;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
