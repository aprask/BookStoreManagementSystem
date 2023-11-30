package Bank;

public interface BankState {
    public void insertCard();
    public void ejectCard();

    /**
     *
     * @param pin retrieve a pin
     */
    public void insertPin(String pin);

    /**
     *
     * @param withdrawAmount retrieve an amount to take out of the bank
     */
    public void withdraw(int withdrawAmount);

}
