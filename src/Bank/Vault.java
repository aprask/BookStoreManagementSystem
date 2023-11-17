package Bank;

public class Vault {
    private BankState hasCard;
    private BankState noCard;
    private BankState correctPin;
    private  BankState noVaultMoney;
    private  BankState vaultState;
    private int cashStoredInVault = 1000000000;
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
    public void changeVaultState(BankState newBankState)
    {
        vaultState = newBankState;
    }
    public void changeCashInVault(int newCashInVault)
    {
        cashStoredInVault = newCashInVault;
    }

    public int getCashStoredInVault() {
        return cashStoredInVault;
    }

    public void setCashStoredInVault(int cashStoredInVault) {
        this.cashStoredInVault = cashStoredInVault;
    }
    public void insertCard()
    {
        vaultState.insertCard();
    }
    public void ejectCard()
    {
        vaultState.ejectCard();
    }
    public void withdraw(int cashToWithdraw) {
        vaultState.withdraw(cashToWithdraw);
    }
    public void insertPin(String pin)
    {
        vaultState.insertPin(pin);
    }

    public boolean isCorrectPin() {
        return isCorrectPin;
    }
    public BankState hasCard()
    {
        return hasCard;
    }
    public BankState noCard()
    {
        return noCard;
    }
    public BankState hasCorrectPin()
    {
        return correctPin;
    }
    public BankState vaultEmpty()
    {
        return noVaultMoney;
    }

    public void setCorrectPin(boolean correctPin) {
        isCorrectPin = correctPin;
    }
}