package com.example.StudentLib.exception;

public class StudentNotFoundException extends RuntimeException {
	public StudentNotFoundException(String message) {
		super(message);
	}

	public StudentNotFoundException() {
        
	}
}
