package bank;

public class Account {
    private int accountNum, balance;

    //Constructor
    public Account(int accountNum, int balance) {
        this.accountNum=accountNum;
        this.balance=balance;
    }

    //Add or remove money from the account
    public synchronized void preformTransaction(int amount) {
        int previousBalance = balance;
        //Verify that the account balance is not getting negative
        while (previousBalance+amount<0) {
            try {
                System.out.println("Trying to perform transaction in account number: " + this.accountNum+", previous balance: " + previousBalance+"\nthe new balance will be negative: " +(previousBalance+amount)+", amount to change: "+amount);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Both positive and negative
        this.balance=previousBalance+amount;
        System.out.println("Performed transaction in account number: " + this.accountNum+", previous balance: " + previousBalance+"\ncurrent balance: " + this.balance+", amount to change: "+amount);
        notifyAll();
    }

    //Getters
    public int getAccountNum() {
        return this.accountNum;
    }

    public synchronized int getBalance() {
        return this.balance;
    }

    //Override toString method
    @Override
    public String toString() {
        return "Account number: "+this.accountNum+", Balance: "+this.balance;
    }
}
