package Factory;

public class DVD extends Item {
    public DVD()
    {

    }
    public DVD(String name, double price, String type, int size)
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
