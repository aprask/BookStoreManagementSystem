package Factory;
public class Book extends Item
{
    public Book()
    {

    }
    public Book(String name, double price, String type, int size)
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
