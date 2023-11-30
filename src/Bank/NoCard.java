package Bank;

public class NoCard implements BankState {
    Vault vault;
    /**
     *
     * @param newVault given a vault object
     */
    public NoCard(Vault newVault)
    {
        vault = newVault;
    }
    @Override
    public void insertCard() {
        System.out.println("Insertion was successful.");
        vault.changeVaultState(vault.hasCard());
    }
    @Override
    public void ejectCard() {
        System.out.println("You need to insert a card before you eject it.");
        System.out.println("Card Ejected.");
    }

    /**
     *
     * @param pin retrieve a pin
     */
    @Override
    public void insertPin(String pin) {
        System.out.println("You need to insert a card before you eject it.");
        System.out.println("Card Ejected.");
    }

    /**
     *
     * @param withdrawAmount retrieve an amount to take out of the bank
     */
    @Override
    public void withdraw(int withdrawAmount) {
        System.out.println("You need to insert a card before you eject it.");
        System.out.println("Card Ejected.");
    }
}
