package com.mg.flashcards.exceptions;

public class AlreadyExistException extends BadRequestException {

    public AlreadyExistException(String message) {
        super(message);
    }
}
