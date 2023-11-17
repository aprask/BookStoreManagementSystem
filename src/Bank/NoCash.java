package Bank;

public class NoCash implements BankState {
    Vault vault;
    public NoCash(Vault newVault)
    {
        vault = newVault;
    }

    @Override
    public void insertCard() {
        System.out.println("No Money");
        System.out.println("Card Ejected.");
    }

    @Override
    public void ejectCard() {
        System.out.println("No Card");
    }

    @Override
    public void insertPin(String pin) {
        System.out.println("No Money");
    }

    @Override
    public void withdraw(int withdrawAmount) {
        System.out.println("No Money");
    }
}
