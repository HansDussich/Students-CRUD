package com.example.students.exception;

public class StudentAlredyExistException extends RuntimeException {
    public StudentAlredyExistException(String message) {
        super(message);
    }
}
