package com.savahl.mebank.codechallenge;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

class Arguments {

    private final String filename;
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    Arguments(final String filename,
              final LocalDateTime fromDateTime,
              final LocalDateTime toDateTime) {
        this.filename = filename;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    String getFilename() {
        return filename;
    }

    LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    LocalDateTime getToDateTime() {
        return toDateTime;
    }

    boolean isValid() {

        // Validate the filename
        if (StringUtils.isBlank(filename)) {
            System.err.println("A filename must be provided");
            return false;
        }

        Path path = Paths.get(filename);

        if (!Files.exists(path) || Files.isDirectory(path) || !Files.isReadable(path)) {
            System.err.println("The file referred to by filename must exists, "
                    + "must not be a directory and must be readable");
            return false;
        }

        // Valid dates
        if (fromDateTime == null || toDateTime == null) {
            System.err.println("The from and to date time must not be null");
            return false;
        }

        if (fromDateTime.isAfter(toDateTime)) {
            System.err.println("The from date time must be after the to date time");
            return false;
        }

        return true;
    }
}
