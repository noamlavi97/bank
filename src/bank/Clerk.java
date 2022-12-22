package bank;

import java.util.Random;

public class Clerk extends Thread {
    private int SLEEP_TIME_MS=100;
    private Transactions transactionsArray;
    private Account[] accounts;

    //Constructor
    public Clerk (Transactions transactionsArray, Account[] accounts) {
        this.transactionsArray=transactionsArray;
        this.accounts=accounts;
    }
    
    public void performTransaction() {
        Transaction currentTransaction;
        int currentAccountNum, currentAmount;
        Random r = new Random();
        currentTransaction=transactionsArray.getTransaction();
        while (currentTransaction!=null) {
            currentAccountNum=currentTransaction.getAccountNum();
            currentAmount=currentTransaction.getAmount();
            for (int i=0; i<accounts.length; i++) {
                if (accounts[i].getAccountNum()==currentAccountNum)
                {
                    accounts[i].preformTransaction(currentAmount);
                }
            }
            try {
                sleep(r.nextInt(SLEEP_TIME_MS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTransaction=transactionsArray.getTransaction();
        }
    }

    //Run method
    @Override
    public void run() {
        super.run();
        performTransaction();
    }
}
