package com.savahl.mebank.codechallenge;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class TransactionCollator {

    /**
     * Collates and summarises the Transactions.
     *
     * @param accountNbr The account to summarise
     * @param from The From DateTime to filter
     * @param to The To DateTime to filter
     * @param transactions The Map of Transactions to collate from
     * @return The Summary of the collated transactions
     */
    TransactionSummary collate(final String accountNbr,
                               final LocalDateTime from,
                               final LocalDateTime to,
                               final Map<String, Transaction> transactions) {

        // extract a Set of Reversed transactions
        final Set<String> reversedTxns = transactions
                .values()
                .stream()
                .filter(t -> TransactionType.REVERSAL == t.getType())
                .map(Transaction::getRelatedTransaction)
                .collect(Collectors.toSet());

        // extract a list of Transactions that are between from and to dates and that
        // are not in the list of reversedTxns and build the TransactionSummary
        int numberOfTransactions = 0;
        float amount = 0.0f;

        for (Transaction transaction : transactions.values()) {

            // between the date and not reversed
            if (isDateTimeBetween(from, to, transaction.getCreateAt())
                    && !reversedTxns.contains(transaction.getTransactionId())) {

                float txnAmount = transaction.getAmount();

                // handle the case where the from and to account numbers are the same
                // if this is the same then the txnAmount = 0;
                if (transaction.getToAccountId().equals(transaction.getFromAccountId())
                        && accountNbr.equals(transaction.getToAccountId())) {
                    txnAmount = 0;
                } else if (accountNbr.equals(transaction.getFromAccountId())) {
                    txnAmount = txnAmount * -1; // debit from the accountNbr
                }

                amount += txnAmount;
                numberOfTransactions++;
            }
        }

        return new TransactionSummary(amount, numberOfTransactions);
    }

    /**
     * This will check to see if value is between the from and to dates (inclusive).
     *
     * @param from  The From {@link LocalDateTime}
     * @param to    The To {@link LocalDateTime}
     * @param value The Value to check
     * @return Whether or not value is between from and to
     */
    private boolean isDateTimeBetween(final LocalDateTime from,
                                      LocalDateTime to,
                                      LocalDateTime value) {
        return value.isAfter(from) && value.isBefore(to);
    }
}
