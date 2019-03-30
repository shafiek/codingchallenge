package com.savahl.mebank.codechallenge;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

class ArgumentParser {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    Arguments parse(final String[] args) {

        String accountId = null;
        String filename = null;
        String fromString = null;
        String toString = null;

        for (int x = 0; x < args.length; x++) {
            if ("-file".equals(args[x])) {
                filename = args[x + 1];
                x++;
            } else if ("-accountId".equals(args[x])) {
                accountId = args[x + 1];
                x++;
            } else if ("-from".equals(args[x])) {
                fromString = args[x + 1];
                x++;
            } else if ("-to".equals(args[x])) {
                toString = args[x + 1];
                x++;
            }
        }

        LocalDateTime from = null;
        if (StringUtils.isNotBlank(fromString)) {
            from = LocalDateTime.parse(fromString, formatter);
        }

        LocalDateTime to = null;
        if (StringUtils.isNotBlank(toString)) {
            to = LocalDateTime.parse(toString, formatter);
        }

        return new Arguments(accountId, filename, from, to);
    }
}
