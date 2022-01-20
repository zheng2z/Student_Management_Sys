package com.example.student_management.exceptions;

public class StudentNotExistException extends RuntimeException{

    public StudentNotExistException(String message) {
        super(message);
    }
}

