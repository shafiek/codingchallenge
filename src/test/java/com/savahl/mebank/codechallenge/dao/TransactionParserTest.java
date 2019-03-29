package com.savahl.mebank.codechallenge.dao;

import com.savahl.mebank.codechallenge.Transaction;
import com.savahl.mebank.codechallenge.TransactionType;
import org.junit.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class TransactionParserTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Test
    public void testPaymentType() {
        TransactionParser parser = new TransactionParser();

        final String line = "TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT";
        Transaction transaction = parser.parseTransaction(line);

        // assert that the values are correct
        assertEquals("TX10001", transaction.getTransactionId());
        assertEquals("ACC334455", transaction.getFromAccountId());
        assertEquals("ACC778899", transaction.getToAccountId());
        assertEquals("20/10/2018 12:47:55", transaction.getCreateAt().format(formatter));
        assertEquals(25.00f, transaction.getAmount(), 0.0f);
        assertEquals(TransactionType.PAYMENT, transaction.getType());
    }

    @Test
    public void testReversalType() {
        TransactionParser parser = new TransactionParser();

        final String line = "TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, REVERSAL, TXN10002";
        Transaction transaction = parser.parseTransaction(line);

        // assert that the values are correct
        assertEquals(TransactionType.REVERSAL, transaction.getType());
        assertEquals("TXN10002", transaction.getRelatedTransaction());
    }
}
