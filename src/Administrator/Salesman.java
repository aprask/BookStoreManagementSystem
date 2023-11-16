package Administrator;

public class Salesman implements Pitch {
    @Override
    public String askForName() {
        return null;
    }

    @Override
    public int askForAge() {
        return 0;
    }

    @Override
    public double askForBalance() {
        return 0;
    }

    @Override
    public String askForPaymentType() {
        return null;
    }

    @Override
    public boolean askForPremium() {
        return false;
    }
}
