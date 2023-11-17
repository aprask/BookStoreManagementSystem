package Factory;

import java.util.ArrayList;

public class Engineer
{
    private CDBuilder cdBuilder;
    private BookBuilder bookBuilder;
    private DVDBuilder dvdBuilder;
    private static ArrayList<Item> catalog = new ArrayList<>();
    public Engineer(int itemType)
    {
        if(itemType == 1)
        {
            cdBuilder = new CDBuilder();
            catalog.add(cdBuilder.buildCD());
        }
        else if(itemType == 2)
        {
            bookBuilder = new BookBuilder();
            catalog.add(bookBuilder.buildBook());
        }
        else if(itemType == 3)
        {
            dvdBuilder = new DVDBuilder();
            catalog.add(dvdBuilder.buildDVD());
        }
    }
    public CDBuilder getCdBuilder() {
        return cdBuilder;
    }

    public void setCdBuilder(CDBuilder cdBuilder) {
        this.cdBuilder = cdBuilder;
    }

    public DVDBuilder getDvdBuilder() {
        return dvdBuilder;
    }

    public void setDvdBuilder(DVDBuilder dvdBuilder) {
        this.dvdBuilder = dvdBuilder;
    }

    public BookBuilder getBookBuilder() {
        return bookBuilder;
    }

    public void setBookBuilder(BookBuilder bookBuilder) {
        this.bookBuilder = bookBuilder;
    }

    public ArrayList<Item> getCatalog() {
        return catalog;
    }

    public void setCatalog(ArrayList<Item> catalog) {
        Engineer.catalog = catalog;
    }
}
