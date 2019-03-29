package com.savahl.mebank.codechallenge.dao;

import com.savahl.mebank.codechallenge.Transaction;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TransactionDaoTest {

    private final String txns = "TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT\n"
    + "TX10002, ACC334455, ACC998877, 20/10/2018 17:33:43, 10.50, PAYMENT\n"
    + "TX10003, ACC998877, ACC778899, 20/10/2018 18:00:00, 5.00, PAYMENT\n"
    + "TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 10.50, REVERSAL, TX10002\n"
    + "TX10005, ACC334455, ACC778899, 21/10/2018 09:30:00, 7.25, PAYMENT";

    @Test
    public void testRetrieveByAccountId() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(txns.getBytes());

        TransactionDao transactionDao = new TransactionDao();

        List<Transaction> transactions =
                transactionDao.getTransactionsByAccountNumber("ACC334455", inputStream);

        assertEquals(4, transactions.size());
    }
}
