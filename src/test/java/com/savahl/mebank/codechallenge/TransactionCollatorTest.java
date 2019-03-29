package com.savahl.mebank.codechallenge;

import static org.junit.Assert.assertEquals;

import com.savahl.mebank.codechallenge.dao.TransactionDao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;

import org.junit.Test;

public class TransactionCollatorTest {

    private final String
            txns = "TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT\n"
            + "TX10002, ACC334455, ACC998877, 20/10/2018 17:33:43, 10.50, PAYMENT\n"
            + "TX10003, ACC998877, ACC778899, 20/10/2018 18:00:00, 5.00, PAYMENT\n"
            + "TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 10.50, REVERSAL, TX10002\n"
            + "TX10005, ACC334455, ACC778899, 21/10/2018 09:30:00, 7.25, PAYMENT";

    @Test
    public void testRetrieveByAccountId() throws IOException {

        // Set up the test
        InputStream inputStream = new ByteArrayInputStream(txns.getBytes());

        TransactionDao transactionDao = new TransactionDao();

        final String accountNbr = "ACC334455";

        Map<String, Transaction> transactions =
                transactionDao.getTransactionsByAccountNumber(accountNbr, inputStream);

        // Perform the action
        LocalDateTime from = LocalDateTime.of(2018, 10, 20, 12, 0, 0);
        LocalDateTime to = LocalDateTime.of(2018, 10, 20, 19, 0, 0);

        TransactionCollator collator = new TransactionCollator();
        TransactionSummary summary = collator.collate(accountNbr, from, to, transactions);

        // Assert the values
        assertEquals(1, summary.getNumberOfTransactions());
        assertEquals(-25.0f, summary.getAmount(), 0.0f);
    }
}
