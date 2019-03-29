package com.savahl.mebank.codechallenge.dao;

import com.google.common.collect.Maps;
import com.savahl.mebank.codechallenge.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class TransactionDao {

    private TransactionParser parser = new TransactionParser();

    /**
     * This will return a Map&gt;TransactionId, Transaction&lt; of Transactions that contain a
     * fromAccount number or to accountNumber that matches accountNbr.
     *
     * @param accountNbr The account number to match
     * @return List of matching Transactions
     * @throws IOException .
     */
    public Map<String, Transaction> getTransactionsByAccountNumber(final String accountNbr,
                                                            final InputStream inputStream)
            throws IOException {

        Map<String, Transaction> transactions = Maps.newLinkedHashMap();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        // Read through all the lines and extract all Transaction that have a from
        // or to account that matches accountNbr
        while ((line = reader.readLine()) != null) {
            Transaction transaction = parser.parseTransaction(line);
            if (transaction.getFromAccountId().equals(accountNbr)
                    || transaction.getFromAccountId().equals(accountNbr)) {
                transactions.put(transaction.getTransactionId(), transaction);
            }
        }


        return transactions;
    }
}
