package Factory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DVDBuilder implements ItemBuilder {
    private DVD dvd;
    private Scanner scanner = new Scanner(System.in);
    public DVDBuilder()
    {
        this.dvd = new DVD();
    }
    @Override
    public void buildItemType() {
        System.out.println("What genre is this DVD? ");
        String genre = "";
        try{
            genre = scanner.next();
        } catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        this.dvd.setItemType(genre);
    }

    @Override
    public void buildItemName() {
        System.out.println("What is the name of the DVD? ");
        String name = "";
        try{
            name = scanner.next();
        } catch (Exception e)
        {
            System.out.println("Error: " + e);
            System.out.println("We will call this dvd \"default_dvd\"");
            name = "default_dvd";
        }
        this.dvd.setItemName(name);
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
        this.dvd.setItemPrice(price);
    }
    @Override
    public void buildItemSize() {
        System.out.println("How many seconds in the DVD? ");
        int size;
        try{
            size = scanner.nextInt();
        } catch(InputMismatchException e)
        {
            System.out.println("Error: " + e);
            System.out.println("We will say that the second count is 1");
            size = 1;
        }
        this.dvd.setItemSize(size);
    }
    public DVD buildDVD()
    {
        buildItemName();
        buildItemPrice();
        buildItemType();
        buildItemSize();
        return new DVD(this.dvd.getItemName(), this.dvd.getItemPrice(), this.dvd.getItemType(), this.dvd.getSize());
    }
    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public DVD getDvd() {
        return dvd;
    }

    public void setDvd(DVD dvd) {
        this.dvd = dvd;
    }
}
