package com.mg.flashcards.services;

import com.mg.flashcards.dtos.CardDto;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.web.requests.CreateCardRequest;
import com.mg.flashcards.web.requests.UpdateCardRequest;

import java.util.List;

public interface CardService {

    void createCard(CreateCardRequest createCardRequest) throws AlreadyExistException, ResourceIsNotFoundException;
    void updateCard(Integer cardId, UpdateCardRequest updateCardRequest) throws AlreadyExistException, ResourceIsNotFoundException;
    void deleteCard(Integer cardId) throws ResourceIsNotFoundException;
    List<CardDto> getAllCards(Integer setId) throws ResourceIsNotFoundException;
}
