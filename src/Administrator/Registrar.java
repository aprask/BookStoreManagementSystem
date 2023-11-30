package Administrator;

import java.util.ArrayList;
public class Registrar
{
    public static final ArrayList<Customer> customerLog = new ArrayList<>();
    public Registrar()
    {

    }

    /**
     *
     * @param customer a customer object passed into the method to add to the customer log history
     */
    public void addCustomerToLog(Customer customer)
    {
        customerLog.add(customer);
    }

    /**
     *
     * @param ID given an ID
     * @return return a set of details relevant to the customer
     */
    public String customerDetails(int ID)
    {
        if(customerLog.get(ID).isPremium() && customerLog.get(ID) != null)
        {
            return "Hello, " + customerLog.get(ID).getName() +
                    "\nYour Payment Type: " + customerLog.get(ID).getPaymentType() +
                    "\nPremium Membership\n";
        }
        else if(!customerLog.get(ID).isPremium() && customerLog.get(ID) != null)
        {
            return "Hello, " + customerLog.get(ID).getName() +
                    "\nYour Payment Type: " + customerLog.get(ID).getPaymentType() +
                    "\nStandard Membership\n";
        }
        else return null;
    }

    /**
     *
     * @param ID given an ID
     * @return return the customer's name by the ID
     */
    public String getCustomerNameByID(int ID){
        return customerLog.get(ID).getName();
    }
}
