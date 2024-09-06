package se.lexicon.exception;

import java.io.IOException;

public class IOErrorException extends IOException {
    public IOErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
