package Factory;

public class CD extends Item {

    public CD()
    {

    }
    public CD(String name, double price, String type, int size)
    {
        super(name,price,type,size);
    }
    @Override
    public String toString() {
        return "Name: " + this.getItemName() +
                "\n Genre: " + this.getItemType() +
                "\n Price: " + this.getItemPrice();
    }
}
