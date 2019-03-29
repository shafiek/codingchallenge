package com.savahl.mebank.codechallenge.dao;

import com.savahl.mebank.codechallenge.Transaction;
import com.savahl.mebank.codechallenge.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is a simple line parser that assumes that all the inputs to it are as expected.
 */
class TransactionParser {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Pares the txnAsString to a {@link Transaction}.  It assumes that txnAsString is in the correct
     * format.  An improveent to this would be to quite a bit of error handling.
     *
     * @param txnAsString The String to parse into a Transaction
     * @return an instance of a {@link Transaction}
     */
    Transaction parseTransaction(final String txnAsString) {
        final String[] parts = txnAsString.split(",");

        String transactionId = parts[0].trim();
        String fromAccountId = parts[1].trim();
        String toAccountId = parts[2].trim();
        LocalDateTime createAt = LocalDateTime.parse(parts[3].trim(), formatter);
        float amount = Float.valueOf(parts[4]);
        TransactionType transactionType = TransactionType.valueOf(parts[5].trim());

        String relatedTransaction = null;

        if(TransactionType.REVERSAL == transactionType) {
            relatedTransaction = parts[6].trim();
        }

        return new Transaction(transactionId,
                fromAccountId,
                toAccountId,
                createAt,
                amount,
                transactionType,
                relatedTransaction);
    }
}
