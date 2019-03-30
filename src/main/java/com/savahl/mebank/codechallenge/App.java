package com.savahl.mebank.codechallenge;

import com.savahl.mebank.codechallenge.dao.TransactionDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

public class App {

    /**
     * This is a main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) throws IOException {
        ArgumentParser argumentParser = new ArgumentParser();
        Arguments arguments = argumentParser.parse(args);

        // make sure the args are correct
        if (!arguments.isValid()) {
            System.exit(-1);
        }

        // Retrieve the Transactions for the matching accountNbr
        TransactionDao transactionDao = new TransactionDao();
        Map<String, Transaction> transactions =
                transactionDao.getTransactionsByAccountNumber(arguments.getAccountNbr(),
                        new FileInputStream(arguments.getFilename()));

        // Collate the transactions
        TransactionCollator collator = new TransactionCollator();
        TransactionSummary transactionSummary = collator.collate(arguments.getAccountNbr(),
                arguments.getFromDateTime(),
                arguments.getToDateTime(), transactions);

        // print the transactionSummary
        DecimalFormat decimalFormat = new DecimalFormat("$0.00");
        String line1 = String.format("Relative balance for the period is: %s",
                decimalFormat.format(transactionSummary.getAmount()));
        System.out.println(line1);


        String line2 = String.format("Number of transactions included is: %s",
                transactionSummary.getNumberOfTransactions());
        System.out.println(line2);

    }

    private static void printUsage() {

    }
}
