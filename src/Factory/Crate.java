package Factory;

import java.util.ArrayList;

public class Crate
{
    private Engineer engineer;
    private ArrayList<Engineer> buildHistory = new ArrayList<>();
    public Crate()
    {

    }
    public void addToBuildHistory(int itemType)
    {
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
    public ArrayList<Engineer> getBuildHistory() {
        return this.buildHistory;
    }

    public void setBuildHistory(ArrayList<Engineer> buildHistory) {
        this.buildHistory = buildHistory;
    }

    public Engineer getEngineer() {
        return engineer;
    }

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
    }
}
