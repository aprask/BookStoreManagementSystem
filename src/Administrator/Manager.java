package Administrator;
public class Manager {
    private final Salesman salesman = new Salesman();
    private final Registrar registrar = new Registrar();
    public Manager()
    {

    }
    public void catalogCustomers()
    {
        int register = this.salesman.lineTotal();
        while(register > 0)
        {
            int age = this.salesman.askForAge();
            double balance = this.salesman.askForBalance();
            String paymentType = this.salesman.askForPaymentType();
            String premiumStatus = this.salesman.askForPremium();
            boolean updatedPremium = premiumStatus.equalsIgnoreCase("y");
            String name = this.salesman.askForName();
            this.registrar.addCustomerToLog(new Customer(name,age,balance,paymentType,updatedPremium));
            register--;
        }
    }
}
