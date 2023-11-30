package Factory.Custom;

import Factory.*;

import java.util.ArrayList;

public class CustomCrate implements Crate
{
    private final ArrayList<Engineer> buildHistory = new ArrayList<>();
    public CustomCrate()
    {

    }

    /**
     *
     * @param itemType given an item type (1 = CD)(2 = Book)(3 = DVD)
     */
    public void addToBuildHistory(int itemType)
    {
        Engineer engineer;
        if(itemType == 1)
        {
            engineer = new Engineer(1);
            this.buildHistory.add(engineer);
        }
        else if(itemType == 2)
        {
            engineer = new Engineer(2);
            this.buildHistory.add(engineer);
        }
        else if(itemType == 3)
        {
            engineer = new Engineer(3);
            this.buildHistory.add(engineer);
        }

    }
    @Override
    public void openCrate()
    {
        System.out.println("Items: \n");
        for(int i = 0; i < buildHistory.size(); i++)
        {
            if(!this.buildHistory.get(i).getCatalog().get(i).isStatus())
            {
                System.out.println("***********************************************************************");
                System.out.println("Name: " + this.buildHistory.get(i).getCatalog().get(i).getItemName());
                System.out.println("Item ID: " + i);
                System.out.println("Item Price: $" + this.buildHistory.get(i).getCatalog().get(i).getItemPrice());
                if(this.buildHistory.get(i).getCatalog().get(i).getClass().equals(Book.class) && !this.buildHistory.get(i).getCatalog().get(i).isStatus())
                {
                    System.out.println("Page Count: " + this.buildHistory.get(i).getCatalog().get(i).getSize());
                }
                else if(this.buildHistory.get(i).getCatalog().get(i).getClass().equals(CD.class) && !this.buildHistory.get(i).getCatalog().get(i).isStatus())
                {
                    System.out.println("CD Length (in seconds): " + this.buildHistory.get(i).getCatalog().get(i).getSize());
                }
                else if(this.buildHistory.get(i).getCatalog().get(i).getClass().equals(DVD.class) && !this.buildHistory.get(i).getCatalog().get(i).isStatus())
                {
                    System.out.println("DVD Length (in seconds): " + this.buildHistory.get(i).getCatalog().get(i).getSize());
                }
                System.out.println("***********************************************************************");
            }
        }
    }

    /**
     *
     * @param itemType show specific item type menu given the item type ID
     */
    @Override
    public void openCrate(int itemType) {
        for (int i = 0; i < buildHistory.size(); i++) {
            if (itemType == 1) {
                if (this.buildHistory.get(i).getCatalog().get(i).getClass().equals(CD.class) && !this.buildHistory.get(i).getCatalog().get(i).isStatus()) {
                    System.out.println("***********************************************************************");
                    System.out.println("Name: " + this.buildHistory.get(i).getCatalog().get(i).getItemName());
                    System.out.println("Item ID: " + i);
                    System.out.println("Item Price: $" + this.buildHistory.get(i).getCatalog().get(i).getItemPrice());
                    System.out.println("CD Length (in seconds): " + this.buildHistory.get(i).getCatalog().get(i).getSize());
                    System.out.println("***********************************************************************");
                }
            } else if (itemType == 2) {
                if (this.buildHistory.get(i).getCatalog().get(i).getClass().equals(Book.class) && !this.buildHistory.get(i).getCatalog().get(i).isStatus()) {
                    System.out.println("***********************************************************************");
                    System.out.println("Name: " + this.buildHistory.get(i).getCatalog().get(i).getItemName());
                    System.out.println("Item ID: " + i);
                    System.out.println("Item Price: $" + this.buildHistory.get(i).getCatalog().get(i).getItemPrice());
                    System.out.println("Page Count: " + this.buildHistory.get(i).getCatalog().get(i).getSize());
                    System.out.println("***********************************************************************");

                }
            } else if (itemType == 3) {
                if (this.buildHistory.get(i).getCatalog().get(i).getClass().equals(DVD.class) && !this.buildHistory.get(i).getCatalog().get(i).isStatus()) {
                    System.out.println("***********************************************************************");
                    System.out.println("Name: " + this.buildHistory.get(i).getCatalog().get(i).getItemName());
                    System.out.println("Item ID: " + i);
                    System.out.println("Item Price: $" + this.buildHistory.get(i).getCatalog().get(i).getItemPrice());
                    System.out.println("DVD Length (in seconds): " + this.buildHistory.get(i).getCatalog().get(i).getSize());
                    System.out.println("***********************************************************************");

                }
            }
        }
    }

    /**
     *
     * @param item1ID item ID
     * @param item2ID item ID
     */
    @Override
    public void compareItemsInCrate(int item1ID, int item2ID)
    {
        for (int i = 0; i < buildHistory.size(); i++) {
            if(i == item1ID)
            {
                Item tmpItem = this.buildHistory.get(i).getCatalog().get(i);
                for(int j = 0; j < buildHistory.size(); j++)
                {
                    if(j == item2ID)
                    {
                        if(tmpItem.compareTo(this.buildHistory.get(j).getCatalog().get(j)) > 0)
                        {
                            System.out.println(tmpItem.getItemName() + " is more expensive than " + this.buildHistory.get(j).getCatalog().get(j).getItemName());
                        }
                        else if(tmpItem.compareTo(this.buildHistory.get(j).getCatalog().get(j)) < 0)
                        {
                            System.out.println(tmpItem.getItemName() + " is cheaper than " + this.buildHistory.get(j).getCatalog().get(j).getItemName());
                        }
                        else
                        {
                            System.out.println(tmpItem.getItemName() + " has the same price as " + this.buildHistory.get(j).getCatalog().get(j).getItemName());
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param itemID given an ID
     * @return return an Item
     */
    @Override
    public Item retrieveSpecifiedItem(int itemID)
    {
        for(int i = 0; i < buildHistory.size(); i++){
            if(itemID == i && this.buildHistory.get(i).getCatalog().get(i) != null)
            {
                return this.buildHistory.get(i).getCatalog().get(i);
            }
        }
        return null;
    }
    /**
     *
     * @param name given a name
     * @return return an Item
     */
    @Override
    public Item retrieveSpecifiedItem(String name)
    {
        int i = 0;
        while (i < buildHistory.size()) {
            if(buildHistory.get(i).getCatalog().get(i).getItemName().equals(name) && this.buildHistory.get(i).getCatalog().get(i) != null)
            {
                return this.buildHistory.get(i).getCatalog().get(i);
            }
            i++;
        }
        return null;
    }

    /**
     *
     * @return the total cost of the crate
     */
    @Override
    public double valueOfCrate()
    {
        int total = 0;
        for(int i = 0; i < buildHistory.size(); i++)
        {
            if(this.buildHistory.get(i).getCatalog().get(i) != null && !this.buildHistory.get(i).getCatalog().get(i).isStatus())
            {
                total+=this.buildHistory.get(i).getCatalog().get(i).getItemPrice();
            }
        }
        return total;
    }
}
