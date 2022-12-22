package bank;

public class Transaction {
    private int accountNum, amount;

    //Constructor
    public Transaction(int accountNum, int balance) {
        this.accountNum=accountNum;
        this.amount=balance;
    }

    //Setters
    public void setAccountNum(int accountNum) {
        this.accountNum=accountNum;
    }

    public void setAmount(int balance) {
        this.amount=balance;
    }

    //Getters
    public int getAccountNum() {
        return this.accountNum;
    }

    public int getAmount() {
        return this.amount;
    }

    //Override toString method
    @Override
    public String toString() {
        return "Account number: "+this.accountNum+", Amount: "+this.amount;
    }
    
}
