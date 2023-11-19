package Factory;

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
        this.dvd.setItemType(this.scanner.next());
    }

    @Override
    public void buildItemName() {
        System.out.println("What is the name of the DVD? ");
        this.dvd.setItemName(this.scanner.next());
    }

    @Override
    public void buildItemPrice() {
        System.out.println("How much does it cost? ");
        this.dvd.setItemPrice(this.scanner.nextDouble());
    }

    @Override
    public void buildItemStatus() {
        this.dvd.setItemStatus(false);
    }

    @Override
    public void buildItemSize() {
        System.out.println("How many seconds in the DVD? ");
        this.dvd.setItemSize(this.scanner.nextInt());
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
