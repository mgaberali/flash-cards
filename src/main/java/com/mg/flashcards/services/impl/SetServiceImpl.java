package com.mg.flashcards.services.impl;

import com.mg.flashcards.dtos.SetDto;
import com.mg.flashcards.entities.Set;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.repositories.SetRepository;
import com.mg.flashcards.services.SetService;
import com.mg.flashcards.utils.BeanMapperUtil;
import com.mg.flashcards.web.requests.CreateSetRequest;
import com.mg.flashcards.web.requests.UpdateSetRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SetServiceImpl implements SetService{

    private static Logger logger = LogManager.getLogger();

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private BeanMapperUtil beanMapperUtil;

    @Override
    public void createSet(CreateSetRequest createSetRequest) throws AlreadyExistException {

        Set foundSet = setRepository.findByName(createSetRequest.getName());
        if(foundSet != null){
            throw new AlreadyExistException("Set with name of " + foundSet.getName() + " is already exists");
        }

        Set set = beanMapperUtil.map(createSetRequest, Set.class);
        set.setCreatedAt(new Date());
        setRepository.save(set);
    }

    @Override
    public void deleteSet(Integer setId) throws ResourceIsNotFoundException {

        Optional<Set> foundSet = setRepository.findById(setId);
        if(!foundSet.isPresent()){
            throw new ResourceIsNotFoundException("Set with id of " + setId + " is not exist");
        }

        setRepository.delete(foundSet.get());
    }

    @Override
    public void updateSet(Integer setId, UpdateSetRequest updateSetRequest) throws ResourceIsNotFoundException {

        Optional<Set> foundSet = setRepository.findById(setId);
        if(!foundSet.isPresent()){
            throw new ResourceIsNotFoundException("Set with id of " + setId + " is not exist");
        }

        beanMapperUtil.map(updateSetRequest, foundSet.get());
        setRepository.save(foundSet.get());
    }

    @Override
    public List<SetDto> getAllSets() {

        List<Set> setList = setRepository.findAll();
        List<SetDto> setDtoList = beanMapperUtil.map(setList, SetDto.class);

        return setDtoList;
    }

}
