package Bank;

public class NoCash implements BankState {
    Vault vault;
    /**
     *
     * @param newVault given a vault object
     */
    public NoCash(Vault newVault)
    {
        vault = newVault;
    }

    @Override
    public void insertCard() {
        System.out.println("No Money");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
        System.out.println("Card Ejected.");
    }

    @Override
    public void ejectCard() {
        System.out.println("No Card");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
    }

    /**
     *
     * @param pin retrieve a pin
     */
    @Override
    public void insertPin(String pin) {
        System.out.println("No Money");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
    }

    /**
     *
     * @param withdrawAmount retrieve an amount to take out of the bank
     */
    @Override
    public void withdraw(int withdrawAmount) {
        System.out.println("No Money");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
    }
}
