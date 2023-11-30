package Factory;

public interface Crate {
    public void openCrate();
    public void openCrate(int itemID);
    public void compareItemsInCrate(int ID1, int ID2);
    public Item retrieveSpecifiedItem(int itemID);
    public Item retrieveSpecifiedItem(String name);
    public double valueOfCrate();
}
