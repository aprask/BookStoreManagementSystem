package Bank;

public class Vault {
    private BankState hasCard;
    private BankState noCard;
    private BankState correctPin;
    private BankState noVaultMoney;
    private BankState vaultState;
    private static int cashStoredInVault = 1000000000;
    private boolean isCorrectPin = false;
    public Vault()
    {
        hasCard = new HasCard(this);
        noCard = new NoCard(this);
        correctPin = new HasPin(this);
        noVaultMoney = new NoCash(this);
        vaultState = noCard;
        if(cashStoredInVault <= 0)
        {
            vaultState = noVaultMoney;
        }

    }

    /**
     *
     * @param newBankState given a new state of the bank instance
     */
    public void changeVaultState(BankState newBankState)
    {
        vaultState = newBankState;
    }

    /**
     *
     * @param newCashInVault given the amount of cash, change the amount within the vault (Replace!)
     */
    public void changeCashInVault(int newCashInVault)
    {
        cashStoredInVault = newCashInVault;
    }

    /**
     *
     * @return return the amount of cash stored in the vault
     */
    public int getCashStoredInVault() {
        return cashStoredInVault;
    }

    /**
     *
     * @return return whether the user has a card
     */
    public BankState hasCard()
    {
        return hasCard;
    }

    /**
     *
     * @return return whether the user has NO card
     */
    public BankState noCard()
    {
        return noCard;
    }

    /**
     *
     * @return if the user has a correct pin
     */
    public BankState hasCorrectPin()
    {
        return correctPin;
    }

    /**
     *
     * @return if the vault is empty
     */
    public BankState vaultEmpty()
    {
        return noVaultMoney;
    }

    /**
     *
     * @param correctPin verify the pin
     */
    public void setCorrectPin(boolean correctPin) {
        isCorrectPin = correctPin;
    }
}