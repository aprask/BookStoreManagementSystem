package Factory;

public class CD extends Item {

    public CD()
    {

    }

    /**
     *
     * @param name a cd name
     * @param price a cd price
     * @param type a cd genre
     * @param size a cd length (in seconds)
     */
    public CD(String name, double price, String type, int size)
    {
        super(name,price,type,size);
    }

    /**
     *
     * @return details of cd
     */
    @Override
    public String toString() {
        return "Name: " + this.getItemName() +
                "\n Genre: " + this.getItemType() +
                "\n Price: " + this.getItemPrice();
    }
}
