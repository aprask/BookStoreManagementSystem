package Factory.Custom;

import Factory.Item;

import java.util.ArrayList;

public class Engineer
{
    private static final ArrayList<Item> catalog = new ArrayList<>();
    /**
     *
     * @param itemType (1 = build CD)(2 = build BOOK)(3 = build DVD)
     */
    public Engineer(int itemType)
    {
        if(itemType == 1)
        {
            CDBuilder cdBuilder = new CDBuilder();
            catalog.add(cdBuilder.buildCD());
        }
        else if(itemType == 2)
        {
            BookBuilder bookBuilder = new BookBuilder();
            catalog.add(bookBuilder.buildBook());
        }
        else if(itemType == 3)
        {
            DVDBuilder dvdBuilder = new DVDBuilder();
            catalog.add(dvdBuilder.buildDVD());
        }
    }
    public ArrayList<Item> getCatalog() {
        return catalog;
    }

}
