package com.savahl.mebank.codechallenge.dao;

import com.google.common.collect.Lists;
import com.savahl.mebank.codechallenge.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class TransactionDao {

    private TransactionParser parser = new TransactionParser();

    TransactionDao() {
    }

    /**
     * This will return a List of Transactions that contain a fromAccount number or
     * to accountNumber that matches accountNbr.
     * @param accountNbr The account number to match
     * @return List of matching Transactions
     * @throws IOException .
     */
    List<Transaction> getTransactionsByAccountNumber(final String accountNbr, final InputStream inputStream)
            throws IOException {

        List<Transaction> transactions = Lists.newArrayList();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while ((line = reader.readLine()) != null) {
            Transaction transaction = parser.parseTransaction(line);
            if (transaction.getFromAccountId().equals(accountNbr)
                    || transaction.getFromAccountId().equals(accountNbr)) {
                transactions.add(transaction);
            }
        }


        return transactions;
    }



}
