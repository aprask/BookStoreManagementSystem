package Administrator;

public class Customer extends Person
{
    private boolean premium;
    private int ID = 0;

    /**
     *
     * @param name retrieve a name
     * @param age retrive an age
     * @param paymentType retrieve a payment type
     * @param premium retrieve a premium status
     */
    public Customer(String name, int age, String paymentType, boolean premium) {
        super(name, age, paymentType);
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
