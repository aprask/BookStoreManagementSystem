package Administrator;

import java.util.ArrayList;
public class Registrar
{
    private static final ArrayList<Customer> customerLog = new ArrayList<>();
    public Registrar()
    {

    }
    public void addCustomerToLog(Customer customer)
    {
        customerLog.add(customer);
    }
    public String runIntegralInfo(int ID)
    {
        return "Name: " + customerLog.get(ID).getName() +
                "\nBalance: " + customerLog.get(ID).getBalance();
    }
}
