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
    public String customerDetails(int ID)
    {
        return customerLog.get(ID).getName();
    }
}
