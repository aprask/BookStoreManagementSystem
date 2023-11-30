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
        System.out.println("Card Ejected.");
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
        System.out.println("Why would you enter multiple pins?");
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
        if(withdrawAmount > vault.getCashStoredInVault())
        {
            System.out.println("You are asking for too much cash");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            System.out.println("We only have $" + vault.getCashStoredInVault());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            System.out.println("Next time, ask for a more reasonable withdraw amount");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            System.out.println("Rejected");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            System.out.println("Card Ejected.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            vault.changeVaultState(vault.noCard());
        }
        else
        {
            System.out.println("You have withdrew $" + withdrawAmount);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            vault.changeCashInVault(vault.getCashStoredInVault() - withdrawAmount);
            System.out.println("Card Ejected.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
            vault.changeVaultState(vault.noCard());
            if (vault.getCashStoredInVault() == 0) vault.changeVaultState(vault.vaultEmpty());
            else System.out.println("Amount left in vault: $" + vault.getCashStoredInVault());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException o) {
                throw new RuntimeException(o);
            }
        }
    }
}