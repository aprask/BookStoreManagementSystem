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
        System.out.println("You can only enter one card at a time");
        System.out.println("Card Ejected!");
    }

    @Override
    public void ejectCard() {
        System.out.println("Card Ejected!");
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
            vault.setCorrectPin(false);
            System.out.println("Rejected!");
            vault.changeVaultState(vault.noCard());
        }
        else
        {
            System.out.println("4 digit pin entered successfully!");
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
    }
}
