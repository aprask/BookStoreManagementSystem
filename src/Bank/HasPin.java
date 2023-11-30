package Bank;

public class HasPin implements BankState {
    Vault vault;

    /**
     *
     * @param newVault given a vault object
     */
    public HasPin(Vault newVault)
    {
        vault = newVault;
    }
    @Override
    public void insertCard() {
        System.out.println("Error");
        System.out.println("You can only enter one card at a time");
        System.out.println("Card Ejected.");
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
        System.out.println("Why would you enter multiple pins?");
    }

    /**
     *
     * @param withdrawAmount retrieve an amount to take out of the bank
     */
    @Override
    public void withdraw(int withdrawAmount) {
        if(withdrawAmount > vault.getCashStoredInVault())
        {
            System.out.println("You are asking for too much cash");
            System.out.println("We only have $" + vault.getCashStoredInVault());
            System.out.println("Next time, ask for a more reasonable withdraw amount");
            System.out.println("Rejected");
            System.out.println("Card Ejected.");
            vault.changeVaultState(vault.noCard());
        }
        else
        {
            System.out.println("You have withdrew $" + withdrawAmount);
            vault.changeCashInVault(vault.getCashStoredInVault() - withdrawAmount);
            System.out.println("Card Ejected.");
            vault.changeVaultState(vault.noCard());
            if (vault.getCashStoredInVault() == 0) vault.changeVaultState(vault.vaultEmpty());
            else System.out.println("Amount left in vault: $" + vault.getCashStoredInVault());
        }
    }
}