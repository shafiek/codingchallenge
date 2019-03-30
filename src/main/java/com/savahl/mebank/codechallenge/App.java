package com.savahl.mebank.codechallenge;

import com.savahl.mebank.codechallenge.dao.TransactionDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Map;

public class App {

    private App() {
    }

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
            printUsage();
            System.exit(-1);
        }

        // Retrieve the Transactions for the matching accountNbr
        TransactionDao transactionDao = new TransactionDao();
        Map<String, Transaction> transactions;

        try (InputStream inputStream = new FileInputStream(arguments.getFilename())) {
            transactions = transactionDao.getTransactionsByAccountNumber(arguments.getAccountNbr(),
                    inputStream);
        }

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
        System.err.println("\nUsage: java -jar {path to jar/codingchallenge.jar} "
                + "-file [file] "
                + "-accountId [accountId] "
                + "-from [from date] "
                + "-to [to date]");

        System.err.println("\t-file\t\t"
                + "A CSV file in the Transaction list format defined by the coding challenge");
        System.err.println("\t-accountId\t"
                + "The accountId for which you would like to print the details");
        System.err.println("\t-from\t\t"
                + "The from Date Time in in the format DD/MM/YYYY hh:mm:ss");
        System.err.println("\t-to\t\t"
                + "The to Date Time in in the format DD/MM/YYYY hh:mm:ss");

        System.err.println("\n\nExample: "
                + "java -jar ./build/libs/codingchallenge.jar "
                + "-file sample.csv "
                + "-accountId ACC334455 "
                + "-from \"20/10/2018 12:00:00\" "
                + "-to \"20/10/2018 19:00:00\"");
    }
}
