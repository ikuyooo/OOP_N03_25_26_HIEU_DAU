// src/main/java/com/example/quanlykhachsan/exception/BookingConflictException.java
package com.example.quanlykhachsan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BookingConflictException extends RuntimeException {
    public BookingConflictException(String message) {
        super(message);
    }
}