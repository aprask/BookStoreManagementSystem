package Factory;

public class DVD extends Item {
    public DVD()
    {

    }

    /**
     *
     * @param name a dvd name
     * @param price a dvd price
     * @param type a dvd genre
     * @param size a dvd length (in seconds)
     */
    public DVD(String name, double price, String type, int size)
    {
        super(name,price,type,size);
    }

    /**
     *
     * @return details of dvd
     */
    @Override
    public String toString() {
        return "Name: " + this.getItemName() +
                "\n Genre: " + this.getItemType() +
                "\n Price: " + this.getItemPrice();
    }
}
