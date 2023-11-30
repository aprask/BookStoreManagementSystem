package Factory;
public class Book extends Item
{
    public Book()
    {

    }

    /**
     *
     * @param name a book name
     * @param price a book price
     * @param type a book genre
     * @param size a book length (page count)
     */
    public Book(String name, double price, String type, int size)
    {
        super(name,price,type,size);
    }

    /**
     *
     * @return details of book
     */
    @Override
    public String toString() {
        return "Name: " + this.getItemName() +
                "\n Genre: " + this.getItemType() +
                "\n Price: " + this.getItemPrice();
    }
}
