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
            System.out.println("***********************************************************************");
            System.out.println("Name: " + this.buildHistory.get(i).getCatalog().get(i).getItemName());
            System.out.println("Item ID: " + i);
            System.out.println("Item Price: $" + this.buildHistory.get(i).getCatalog().get(i).getItemPrice());
            if(this.buildHistory.get(i).getCatalog().get(i).getClass().equals(Book.class))
            {
                System.out.println("Page Count: " + this.buildHistory.get(i).getCatalog().get(i).getSize());
            }
            else if(this.buildHistory.get(i).getCatalog().get(i).getClass().equals(CD.class))
            {
                System.out.println("CD Length (in seconds): " + this.buildHistory.get(i).getCatalog().get(i).getSize());
            }
            else if(this.buildHistory.get(i).getCatalog().get(i).getClass().equals(DVD.class))
            {
                System.out.println("DVD Length (in seconds): " + this.buildHistory.get(i).getCatalog().get(i).getSize());
            }
            System.out.println("***********************************************************************");
        }
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
