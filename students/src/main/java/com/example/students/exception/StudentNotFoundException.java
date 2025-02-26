package com.example.students.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
