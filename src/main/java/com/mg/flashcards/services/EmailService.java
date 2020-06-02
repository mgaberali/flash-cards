package com.mg.flashcards.services;


public interface EmailService {

    void send(final String to, final String subjsct, final String text) throws Exception;

}
