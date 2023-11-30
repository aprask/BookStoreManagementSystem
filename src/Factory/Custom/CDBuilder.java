package Factory.Custom;

import Factory.CD;
import Factory.ItemBuilder;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CDBuilder implements ItemBuilder {
    private final CD cd;
    private final Scanner scanner = new Scanner(System.in);
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
        String name;
        try{
            name = scanner.next();
        } catch (Exception e)
        {
            System.out.println("Error: " + e);
            System.out.println("This book will be called \"default_cd\"");
            name = "default_cd";
        }
        this.cd.setItemName(name);
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
        this.cd.setItemPrice(price);
    }
    @Override
    public void buildItemSize() {
        System.out.println("How many seconds in the CD? ");
        int size;
        try{
            size = scanner.nextInt();
        } catch(InputMismatchException e)
        {
            System.out.println("Error: " + e);
            System.out.println("We will say that the second count is 1");
            size = 1;
        }
        this.cd.setItemSize(size);
    }

    /**
     *
     * @return build a CD
     */
    public CD buildCD()
    {
        buildItemName();
        buildItemPrice();
        buildItemType();
        buildItemSize();
        return new CD(this.cd.getItemName(), this.cd.getItemPrice(), this.cd.getItemType(), this.cd.getSize());
    }
    /**
     *
     * @param name a cd name (Alternate)
     * @param price a cd price (Alternate)
     * @param itemType a cd genre (Alternate)
     * @param size a cd length (Alternate)
     * @return return a CD object
     */
    public CD buildCD(String name, double price, String itemType, int size)
    {
        return new CD(name,price,itemType,size);
    }
}