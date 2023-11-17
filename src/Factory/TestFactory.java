package Factory;

import java.util.ArrayList;

public class TestFactory
{
    public static void main(String[] args)
    {
        Crate crate = new Crate();
        crate.addToBuildHistory(1);
        crate.openCrate();
    }
}
