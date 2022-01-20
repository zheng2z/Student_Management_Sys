package com.example.student_management.exceptions;

public class StudentEmptyNameException extends RuntimeException{

    public StudentEmptyNameException(String message) {
        super(message);
    }
}
