package Bank;

public class NoCard implements BankState{
    Vault vault;
    public NoCard(Vault newVault)
    {
        vault = newVault;
    }
    @Override
    public void insertCard() {
        System.out.println("Enter a pin: ");
        vault.changeVaultState(vault.hasCard());
    }

    @Override
    public void ejectCard() {
        System.out.println("You need to insert a card before you eject it.");
        System.out.println("Card Ejected.");
    }

    @Override
    public void insertPin(String pin) {
        System.out.println("You need to insert a card before you eject it.");
        System.out.println("Card Ejected.");
    }

    @Override
    public void withdraw(int withdrawAmount) {
        System.out.println("You need to insert a card before you eject it.");
        System.out.println("Card Ejected.");
    }
}
