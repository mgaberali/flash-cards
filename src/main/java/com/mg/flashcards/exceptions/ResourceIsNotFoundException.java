package com.mg.flashcards.exceptions;

public class ResourceIsNotFoundException extends BadRequestException {

    public ResourceIsNotFoundException(String message) {
        super(message);
    }
}
