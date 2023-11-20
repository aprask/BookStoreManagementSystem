package Administrator;

import java.util.ArrayList;
public class Registrar
{
    public static final ArrayList<Customer> customerLog = new ArrayList<>();
    public Registrar()
    {

    }
    public void addCustomerToLog(Customer customer)
    {
        customerLog.add(customer);
    }
    public String customerDetails(int ID)
    {
        if(customerLog.get(ID).isPremium() && customerLog.get(ID) != null)
        {
            return "Hello, " + customerLog.get(ID).getName() +
                    "\nYour Payment Type: " + customerLog.get(ID).getPaymentType() +
                    "\nCongratulations :D on your premium member purchase: \n";
        }
        else if(!customerLog.get(ID).isPremium() && customerLog.get(ID) != null)
        {
            return "Hello, " + customerLog.get(ID).getName() +
                    "\nYour Payment Type: " + customerLog.get(ID).getPaymentType() +
                    "\nStandard Membership ;(";
        }
        else return null;
    }
}
