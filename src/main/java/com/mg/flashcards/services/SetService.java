package com.mg.flashcards.services;

import com.mg.flashcards.dtos.SetDto;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.web.requests.CreateSetRequest;
import com.mg.flashcards.web.requests.UpdateSetRequest;

import java.util.List;

public interface SetService {

    void createSet(CreateSetRequest createSetRequest) throws AlreadyExistException;
    void deleteSet(Integer setId) throws ResourceIsNotFoundException;
    void updateSet(Integer setId, UpdateSetRequest updateSetRequest) throws ResourceIsNotFoundException;
    List<SetDto> getAllSets();
}
