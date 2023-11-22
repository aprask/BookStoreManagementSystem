package Administrator;
public class Manager {
    private final Salesman salesman = new Salesman();
    public final Registrar registrar = new Registrar();
    private int lineCount = 0;
    public Manager()
    {

    }
    public void catalogCustomers()
    {
        int log = this.salesman.lineTotal();
        lineCount = log;
        int customerLocation = 1;
        while(log > 0)
        {
            System.out.println("------------------------------------");
            System.out.println("\n\tCustomer #" + customerLocation);
            int age = this.salesman.askForAge();
            String paymentType = this.salesman.askForPaymentType();
            String premiumStatus = this.salesman.askForPremium();
            boolean updatedPremium = premiumStatus.equalsIgnoreCase("y");
            String name = this.salesman.askForName();
            this.registrar.addCustomerToLog(new Customer(name,age,paymentType,updatedPremium));
            customerLocation++;
            log--;
        }
    }
    public void membershipDueDate()
    {
        this.salesman.membershipDueDate();
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }
}
