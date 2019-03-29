package com.savahl.mebank.codechallenge;

class TransactionSummary {

    private final float amount;
    private final int numberOfTransactions;

    TransactionSummary(float amount, int numberOfTransactions) {
        this.amount = amount;
        this.numberOfTransactions = numberOfTransactions;
    }

    float getAmount() {
        return amount;
    }

    int getNumberOfTransactions() {
        return numberOfTransactions;
    }
}
