package Factory;
import java.util.*;
public class BookBuilder implements ItemBuilder
{
    private Book book;
    private Scanner scanner = new Scanner(System.in);
    public BookBuilder()
    {
        this.book = new Book();
    }

    @Override
    public void buildItemType() {
        System.out.println("What genre is this book? ");
        String genre = "";
        try{
            genre = scanner.next();
        } catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        this.book.setItemType(genre);
    }

    @Override
    public void buildItemName() {
        System.out.println("What is the name of the book? ");
        String name;
        try{
            name = scanner.next();
        } catch (Exception e)
        {
            System.out.println("Error: " + e);
            System.out.println("This book will be called \"default_book\"");
            name = "default_book";
        }
        this.book.setItemName(name);
    }

    @Override
    public void buildItemPrice() {
        System.out.println("How much does it cost? ");
        double price;
        try {
            price = scanner.nextDouble();
        } catch (InputMismatchException e)
        {
            System.out.println("Invalid Price");
            System.out.println("We will put this item at $0");
            price = 0;
        } catch (Exception e)
        {
            System.out.println("Error: " + e);
            System.out.println("We will put this item at $0");
            price = 0;
        }
        scanner.nextLine();
        this.book.setItemPrice(price);
    }
    @Override
    public void buildItemSize() {
        System.out.println("How many pages? ");
        int size;
        try{
            size = scanner.nextInt();
        } catch(InputMismatchException e)
        {
            System.out.println("Error: " + e);
            System.out.println("We will say that the page count is 1");
            size = 1;
        }
        this.book.setItemSize(size);
    }
    public Book buildBook()
    {
        buildItemName();
        buildItemPrice();
        buildItemType();
        buildItemSize();
        return new Book(this.book.getItemName(), this.book.getItemPrice(), this.book.getItemType(), this.book.getSize());
    }
    public Book buildBook(String name, double price, String itemType, int size)
    {
        return new Book(name,price,itemType,size);
    }
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
