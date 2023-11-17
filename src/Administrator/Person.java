package Administrator;

public abstract class Person {
    private String name;
    private int age;
    private double balance;
    private String paymentType;
    public Person(String name, int age, double balance, String paymentType)
    {
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.paymentType = paymentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
