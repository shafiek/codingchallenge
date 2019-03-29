package com.savahl.mebank.codechallenge;

import static org.junit.Assert.assertEquals;

import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class ArgumentParserTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Test
    public void testValidArgs() {

        // setup test
        String file = "filename";
        String from = "20/10/2018 12:47:55";
        String to = "20/10/2018 19:47:55";

        String[] args = new String[]{"-file", file, "-from", from, "-to", to};

        // perform the action
        ArgumentParser parser = new ArgumentParser();
        Arguments argument = parser.parse(args);

        // assert the results
        assertEquals(file, argument.getFilename());
        assertEquals(from, formatter.format(argument.getFromDateTime()));
        assertEquals(to, formatter.format(argument.getToDateTime()));
    }
}
