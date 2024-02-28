package org.example.exceptions;

public class CompletedTasksNotFoundException extends RuntimeException {
    public CompletedTasksNotFoundException(String message) {
        super(message);
    }
}
