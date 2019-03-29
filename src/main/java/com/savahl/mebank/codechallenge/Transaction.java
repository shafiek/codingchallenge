package com.savahl.mebank.codechallenge;

import java.time.LocalDateTime;

public class Transaction {
    private final String transactionId;
    private final String fromAccountId;
    private final String toAccountId;
    private final LocalDateTime createAt;
    private final float amount;
    private final TransactionType type;
    private final String relatedTransaction;

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

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", fromAccountId='" + fromAccountId + '\'' +
                ", toAccountId='" + toAccountId + '\'' +
                ", createAt=" + createAt +
                ", amount=" + amount +
                ", type=" + type +
                ", relatedTransaction='" + relatedTransaction + '\'' +
                '}';
    }
}
