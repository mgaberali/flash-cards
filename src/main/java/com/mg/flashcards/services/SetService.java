package com.mg.flashcards.services;

import com.mg.flashcards.dtos.SetDto;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.web.requests.CreateSetRequest;
import com.mg.flashcards.web.requests.UpdateSetRequest;

import java.util.List;

public interface SetService {

    void createSet(CreateSetRequest createSetRequest, String userEmail) throws AlreadyExistException;
    void deleteSet(Integer setId, String userEmail) throws ResourceIsNotFoundException;
    void updateSet(Integer setId, UpdateSetRequest updateSetRequest, String userEmail) throws ResourceIsNotFoundException, AlreadyExistException;
    List<SetDto> getAllSets(String userEmail);
}
