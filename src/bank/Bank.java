package bank;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
    
    public static void main(String[] args) {
        final int NUM_OF_ACCOUNTS=5, NUM_OF_TRANSACTIONS=10, DEFAULT_BALANCE=0, MAX_AMOUNT=1000, NUM_OF_CLERKS=10;
        
        //Create accounts
        Account[] accounts = new Account[NUM_OF_ACCOUNTS];
        for (int i=0; i<NUM_OF_ACCOUNTS; i++) {
            accounts[i]=new Account(i, DEFAULT_BALANCE);
        }

        //Create transactions
        Random r=new Random();
        ArrayList<Transaction> transactionsArray = new ArrayList<Transaction>();
        Transactions transactionsArrayVariable = new Transactions(transactionsArray);
        for (int i=0; i<NUM_OF_TRANSACTIONS; i++) {
            transactionsArrayVariable.addTransaction(new Transaction(r.nextInt(NUM_OF_ACCOUNTS), r.nextInt(MAX_AMOUNT*2)-MAX_AMOUNT));
        }

        //Create clerks
        Clerk[] clerks = new Clerk[NUM_OF_CLERKS];
        Thread[] clerkThreads = new Thread[NUM_OF_CLERKS];
        for (int i=0; i<NUM_OF_CLERKS; i++) {
            clerks[i]=new Clerk(transactionsArrayVariable, accounts);
            clerkThreads[i]=new Thread(clerks[i]);
        }

        //Print the accounts
        System.out.println("The accounts before applying the transactions:");
        for (int i=0; i<NUM_OF_ACCOUNTS; i++) {
            System.out.println(accounts[i]);
        }
        //Print the transactions
        System.out.println("The transactions to be applied:");
        System.out.println(transactionsArrayVariable);

        //Apply the transactions
        for (int i=0; i<NUM_OF_CLERKS; i++) {
            clerkThreads[i].start();
        }

        //Wait for the threads to finish
        for (int i=0; i<NUM_OF_CLERKS; i++) { 
            try {
                clerkThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Print the accounts
        System.out.println("The accounts after applying the transactions:");
        for (int i=0; i<NUM_OF_ACCOUNTS; i++) {
            System.out.println(accounts[i]);
        }
    }   
}