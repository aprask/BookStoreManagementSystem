package Factory;

public class TestFactory
{
    public static void main(String[] args)
    {
        Engineer engineer = new Engineer(2);
        System.out.println(engineer.getCatalog());
    }
}
