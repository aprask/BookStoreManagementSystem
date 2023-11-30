package Factory.Standard;

import Factory.*;
import Factory.Custom.BookBuilder;
import Factory.Custom.CDBuilder;
import Factory.Custom.DVDBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DefaultCrate implements Crate
{
    private final ArrayList<Item> defaultItemHistory = new ArrayList<>();
    protected final String PATH_BOOKS = "src/Factory/default_books.csv";
    protected final String PATH_CDS = "src/Factory/default_cds.csv";
    protected final String PATH_DVDS = "src/Factory/default_dvds.csv";
    protected String line = "";
    public DefaultCrate() {
        BufferedReader bufferedReader;
        {
            try {
                bufferedReader = new BufferedReader(new FileReader(PATH_BOOKS));
                while((line = bufferedReader.readLine()) != null)
                {
                    String[] strings = line.split(",");
                    String name = strings[0];
                    double price = Double.parseDouble(strings[1]);
                    String genre = strings[2];
                    double size = Double.parseDouble(strings[3]);
                    BookBuilder bookBuilder = new BookBuilder();
                    defaultItemHistory.add(bookBuilder.buildBook(name,price,genre,(int) size));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        {
            try {
                bufferedReader = new BufferedReader(new FileReader(PATH_CDS));
                while((line = bufferedReader.readLine()) != null)
                {
                    String[] strings = line.split(",");
                    String name = strings[0];
                    double price = Double.parseDouble(strings[1]);
                    String genre = strings[2];
                    double size = Double.parseDouble(strings[3]);
                    CDBuilder cdBuilder = new CDBuilder();
                    defaultItemHistory.add(cdBuilder.buildCD(name,price,genre,(int) size));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        {
            try {
                bufferedReader = new BufferedReader(new FileReader(PATH_DVDS));
                while((line = bufferedReader.readLine()) != null)
                {
                    String[] strings = line.split(",");
                    String name = strings[0];
                    double price = Double.parseDouble(strings[1]);
                    String genre = strings[2];
                    double size = Double.parseDouble(strings[3]);
                    DVDBuilder dvdBuilder = new DVDBuilder();
                    defaultItemHistory.add(dvdBuilder.buildDVD(name,price,genre,(int) size));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void openCrate()
    {
        for(int i = 0; i < defaultItemHistory.size(); i++)
        {
            if(!defaultItemHistory.get(i).isStatus() && defaultItemHistory.get(i) != null)
            {
                System.out.println("********************************************");
                System.out.println("Name: " + defaultItemHistory.get(i).getItemName());
                System.out.println("Price: $" + defaultItemHistory.get(i).getItemPrice());
                System.out.println("ID: " + i);
                if(defaultItemHistory.get(i).getClass().equals(Book.class))
                {
                    System.out.println("Page Count: " + defaultItemHistory.get(i).getSize());
                }
                else if(defaultItemHistory.get(i).getClass().equals(CD.class))
                {
                    System.out.println("CD Length (in seconds): " + defaultItemHistory.get(i).getSize());
                }
                else if(defaultItemHistory.get(i).getClass().equals(DVD.class))
                {
                    System.out.println("DVD Length (in seconds): " + defaultItemHistory.get(i).getSize());
                }
                System.out.println("********************************************");

            }
        }
    }

    /**
     *
     * @param itemType show specific item type menu given the item type ID
     */
    @Override
    public void openCrate(int itemType)
    {
        for(int i = 0; i < defaultItemHistory.size(); i++)
        {
            if(itemType == 1)
            {
                if(!defaultItemHistory.get(i).isStatus() && defaultItemHistory.get(i) != null && defaultItemHistory.get(i).getClass().equals(CD.class))
                {
                    System.out.println("********************************************");
                    System.out.println("Name: " + defaultItemHistory.get(i).getItemName());
                    System.out.println("Price: $" + defaultItemHistory.get(i).getItemPrice());
                    System.out.println("ID: " + i);
                    System.out.println("CD Length (in seconds): " + defaultItemHistory.get(i).getSize());
                    System.out.println("********************************************");

                }
            }
            else if(itemType == 2)
            {
                if(!defaultItemHistory.get(i).isStatus() && defaultItemHistory.get(i) != null && defaultItemHistory.get(i).getClass().equals(Book.class))
                {
                    System.out.println("********************************************");
                    System.out.println("Name: " + defaultItemHistory.get(i).getItemName());
                    System.out.println("Price: $" + defaultItemHistory.get(i).getItemPrice());
                    System.out.println("ID: " + i);
                    System.out.println("Page Count: " + defaultItemHistory.get(i).getSize());
                    System.out.println("********************************************");

                }
            }
            else if(itemType == 3)
            {
                if(!defaultItemHistory.get(i).isStatus() && defaultItemHistory.get(i) != null && defaultItemHistory.get(i).getClass().equals(DVD.class))
                {
                    System.out.println("********************************************");
                    System.out.println("Name: " + defaultItemHistory.get(i).getItemName());
                    System.out.println("Price: $" + defaultItemHistory.get(i).getItemPrice());
                    System.out.println("ID: " + i);
                    System.out.println("DVD Length (in seconds): " + defaultItemHistory.get(i).getSize());
                    System.out.println("********************************************");

                }
            }
        }
    }
    @Override
    public void compareItemsInCrate(int item1ID, int item2ID)
    {
        for (int i = 0; i < this.defaultItemHistory.size(); i++) {
            if(i == item1ID)
            {
                Item tmpItem = this.defaultItemHistory.get(i);
                for(int j = 0; j < this.defaultItemHistory.size(); j++)
                {
                    if(j == item2ID)
                    {
                        if(tmpItem.compareTo(this.defaultItemHistory.get(j)) > 0)
                        {
                            System.out.println(tmpItem.getItemName() + " is more expensive than " + this.defaultItemHistory.get(j).getItemName());
                        }
                        else if(tmpItem.compareTo(this.defaultItemHistory.get(j)) < 0)
                        {
                            System.out.println(tmpItem.getItemName() + " is cheaper than " + this.defaultItemHistory.get(j).getItemName());
                        }
                        else
                        {
                            System.out.println(tmpItem.getItemName() + " has the same price as " + this.defaultItemHistory.get(j).getItemName());
                        }
                    }
                }
            }
        }
    }
    @Override
    public Item retrieveSpecifiedItem(int itemID)
    {
        for(int i = 0; i < defaultItemHistory.size(); i++){
            if(itemID == i && defaultItemHistory.get(i) != null)
            {
                return defaultItemHistory.get(i);
            }
        }
        return null;
    }
    @Override
    public Item retrieveSpecifiedItem(String name)
    {
        int i = 0;
        while (i < defaultItemHistory.size()) {
            if(defaultItemHistory.get(i).getItemName().equals(name) && defaultItemHistory.get(i) != null)
            {
                return defaultItemHistory.get(i);
            }
            i++;
        }
        return null;
    }
    @Override
    public double valueOfCrate()
    {
        int total = 0;
        for (Item item : defaultItemHistory) {
            if (item != null && !item.isStatus()) {
                total += item.getItemPrice();
            }
        }
        return total;
    }
}
