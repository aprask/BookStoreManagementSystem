package Bank;

public interface BankState {
    public void insertCard();
    public void ejectCard();
    public void insertPin(String pin);
    public void withdraw(int withdrawAmount);

}
