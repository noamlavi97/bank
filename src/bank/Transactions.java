package bank;

import java.util.ArrayList;

public class Transactions {
    private ArrayList<Transaction> transactionsArrayVariable = new ArrayList<Transaction>();

    //Constructor
    public Transactions(ArrayList<Transaction> transactionsArray) {
        this.transactionsArrayVariable=transactionsArray;
    }

    //Get transaction - synchronized because different Clerks might remove transactions at the same time
    public synchronized Transaction getTransaction() {
        int arraySize = transactionsArrayVariable.size();
        if (arraySize==0) { return null;}
        int lastTransactionIndex = arraySize-1;
        Transaction returnTransaction=transactionsArrayVariable.get(lastTransactionIndex);
        this.transactionsArrayVariable.remove(returnTransaction);
        return returnTransaction;
    }

    //Add transaction
    public void addTransaction(Transaction toAdd) {
        transactionsArrayVariable.add(toAdd);
    }

    //Override toString method
    @Override
    public String toString() {
        String returnString="";
        for (int i=0; i<transactionsArrayVariable.size(); i++) 
        {
            returnString=returnString+transactionsArrayVariable.get(i)+"\n";
        }
        return returnString;
    }
}