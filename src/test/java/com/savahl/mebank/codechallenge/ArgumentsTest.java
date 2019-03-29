package com.savahl.mebank.codechallenge;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import org.junit.Test;

public class ArgumentsTest {

    @Test
    public void testValidArguments() throws IOException {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusHours(1);

        Path tempFile = Files.createTempFile("test", "csv");
        Arguments arguments = new Arguments(tempFile.toAbsolutePath().toString(), from, to);

        assertTrue(arguments.isValid());
    }

    @Test
    public void testValidDates() throws IOException {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().minusHours(1);

        Path tempFile = Files.createTempFile("test", "csv");
        Arguments arguments = new Arguments(tempFile.toAbsolutePath().toString(), from, to);

        assertFalse(arguments.isValid());
    }

    @Test
    public void testUnknownFile() throws IOException {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusHours(1);

        Arguments arguments = new Arguments("unknown.csv", from, to);

        assertFalse(arguments.isValid());
    }
}
