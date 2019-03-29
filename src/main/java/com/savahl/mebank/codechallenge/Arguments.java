package com.savahl.mebank.codechallenge;

import java.time.LocalDateTime;

class Arguments {

    private final String filename;
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    public Arguments(String filename, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.filename = filename;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public String getFilename() {
        return filename;
    }

    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    public LocalDateTime getToDateTime() {
        return toDateTime;
    }
}
