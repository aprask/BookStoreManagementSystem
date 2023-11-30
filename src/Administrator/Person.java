package Administrator;

public abstract class Person {
    private String name;
    private int age;
    private String paymentType;

    /**
     *
     * @param name retrieve a name
     * @param age retrive an age
     * @param paymentType retrieve a payment type
     */
    public Person(String name, int age, String paymentType)
    {
        this.name = name;
        this.age = age;
        this.paymentType = paymentType;
    }

    /**
     *
     * @return return a name
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
     * @return get the person's payment type
     */
    public String getPaymentType() {
        return paymentType;
    }
}
