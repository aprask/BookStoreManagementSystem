package Factory;

import java.util.Scanner;

public class CDBuilder implements ItemBuilder {
    private CD cd;
    private Scanner scanner = new Scanner(System.in);
    public CDBuilder()
    {
        this.cd = new CD();
    }
    @Override
    public void buildItemType() {
        System.out.println("What genre is this CD? ");
        this.cd.setItemType(this.scanner.next());
    }
    @Override
    public void buildItemName() {
        System.out.println("What is the name of the CD? ");
        this.cd.setItemName(this.scanner.next());
    }
    @Override
    public void buildItemPrice() {
        System.out.println("How much does it cost? ");
        this.cd.setItemPrice(this.scanner.nextDouble());
    }
    @Override
    public void buildItemStatus() {
        this.cd.setItemStatus();
    }
    @Override
    public void buildItemID() {
        this.cd.setItemID();
    }
    @Override
    public void buildItemSize() {
        System.out.println("How many seconds in the CD? ");
        this.cd.setItemSize(this.scanner.nextInt());
    }
    public Book buildCD()
    {
        buildItemName();
        buildItemPrice();
        buildItemType();
        buildItemSize();
        buildItemID();
        return new Book(this.cd.getItemName(), this.cd.getItemPrice(), this.cd.getItemType(), this.cd.getSize());
    }
    public CD getCd() {
        return cd;
    }
    public void setCd(CD cd) {
        this.cd = cd;
    }
    public Scanner getScanner() {
        return scanner;
    }
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}