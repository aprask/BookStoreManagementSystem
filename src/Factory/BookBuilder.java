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
        this.book.setItemType(this.scanner.next());
    }

    @Override
    public void buildItemName() {
        System.out.println("What is the name of the book? ");
        this.book.setItemName(this.scanner.next());
    }

    @Override
    public void buildItemPrice() {
        System.out.println("How much does it cost? ");
        this.book.setItemPrice(this.scanner.nextDouble());
    }

    @Override
    public void buildItemStatus() {
        this.book.setItemStatus();
    }

    @Override
    public void buildItemSize() {
        System.out.println("How many pages? ");
        this.book.setItemSize(this.scanner.nextInt());
    }
    public Book buildBook()
    {
        buildItemName();
        buildItemPrice();
        buildItemType();
        buildItemSize();
        return new Book(this.book.getItemName(), this.book.getItemPrice(), this.book.getItemType(), this.book.getSize());
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
