package Bank;

public class HasCard implements BankState{
    Vault vault;

    /**
     *
     * @param newVault given a vault object
     */
    public HasCard(Vault newVault)
    {
        vault = newVault;
    }
    @Override
    public void insertCard() {
        System.out.println("Error");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
        System.out.println("You can only enter one card at a time");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
        System.out.println("Card Ejected!");
    }

    @Override
    public void ejectCard() {
        System.out.println("Card Ejected!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
        vault.changeVaultState(vault.noCard());
    }

    /**
     *
     * @param pin retrieve a pin
     */
    @Override
    public void insertPin(String pin) {
        if(pin.length() != 4)
        {
            System.out.println("A pin must have 4 digits...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            vault.setCorrectPin(false);
            System.out.println("Rejected!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            vault.changeVaultState(vault.noCard());
        }
        else
        {
            System.out.println("4 digit pin entered successfully!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            vault.setCorrectPin(true);
            vault.changeVaultState(vault.hasCorrectPin());
        }
    }

    /**
     *
     * @param withdrawAmount retrieve an amount to take out of the bank
     */
    @Override
    public void withdraw(int withdrawAmount) {
        System.out.println("TYPE IN PIN FIRST ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException o) {
            throw new RuntimeException(o);
        }
    }
}
