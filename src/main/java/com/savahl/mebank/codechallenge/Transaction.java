package com.savahl.mebank.codechallenge;

import java.time.LocalDateTime;

/**
 * An immutable class that holds the {@link Transaction} details.
 */
public class Transaction {
    private final String transactionId;
    private final String fromAccountId;
    private final String toAccountId;
    private final LocalDateTime createAt;
    private final float amount;
    private final TransactionType type;
    private final String relatedTransaction;

    /**
     * Creates an instance of a Transaction.
     *
     * @param transactionId Transaction Id
     * @param fromAccountId From Account ID
     * @param toAccountId To Account ID
     * @param createAt The date and time the {@link Transaction} occurred
     * @param amount The amount related to the {@link Transaction}
     * @param type The type of the Transaction {@link TransactionType}
     * @param relatedTransaction If the {@link TransactionType} is REVERSAL then this is the
     *                           related {@link Transaction} Id
     */
    public Transaction(String transactionId,
                       String fromAccountId,
                       String toAccountId,
                       LocalDateTime createAt,
                       float amount,
                       TransactionType type,
                       String relatedTransaction) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.createAt = createAt;
        this.amount = amount;
        this.type = type;
        this.relatedTransaction = relatedTransaction;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public float getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }
}
