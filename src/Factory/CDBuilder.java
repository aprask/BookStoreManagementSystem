package Factory;

import java.util.InputMismatchException;
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
        String genre = "";
        try{
            genre = scanner.next();
        } catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        this.cd.setItemType(genre);
    }
    @Override
    public void buildItemName() {
        System.out.println("What is the name of the CD? ");
        String name = "";
        try{
            name = scanner.next();
        } catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        this.cd.setItemName(name);
    }
    @Override
    public void buildItemPrice() {
        System.out.println("How much does it cost? ");
        double price = 0;
        try {
            price = scanner.nextDouble();
        } catch (InputMismatchException e)
        {
            System.out.println("Invalid Price");
        } catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        this.cd.setItemPrice(price);
    }
    @Override
    public void buildItemSize() {
        System.out.println("How many seconds in the CD? ");
        int size = 0;
        try{
            size = scanner.nextInt();
        } catch(InputMismatchException e)
        {
            System.out.println("Error: " + e);
        }
        this.cd.setItemSize(size);
    }
    public CD buildCD()
    {
        buildItemName();
        buildItemPrice();
        buildItemType();
        buildItemSize();
        return new CD(this.cd.getItemName(), this.cd.getItemPrice(), this.cd.getItemType(), this.cd.getSize());
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