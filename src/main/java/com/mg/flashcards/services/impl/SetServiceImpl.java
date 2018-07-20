package com.mg.flashcards.services.impl;

import com.mg.flashcards.dtos.SetDto;
import com.mg.flashcards.entities.Set;
import com.mg.flashcards.entities.User;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.repositories.SetRepository;
import com.mg.flashcards.repositories.UserRepository;
import com.mg.flashcards.services.SetService;
import com.mg.flashcards.utils.BeanMapperUtil;
import com.mg.flashcards.rest.requests.CreateSetRequest;
import com.mg.flashcards.rest.requests.UpdateSetRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SetServiceImpl implements SetService{

    private static Logger logger = LogManager.getLogger();

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private BeanMapperUtil beanMapperUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createSet(CreateSetRequest createSetRequest, String userEmail) throws AlreadyExistException {

        User user = userRepository.findByEmail(userEmail);

        Set foundSet = setRepository.findByUserAndName(user, createSetRequest.getName());
        if(foundSet != null){
            throw new AlreadyExistException("Set with name of " + foundSet.getName() + " is already exists");
        }

        Set set = beanMapperUtil.map(createSetRequest, Set.class);
        set.setCreatedAt(new Date());
        set.setUser(user);

        setRepository.save(set);
    }

    @Override
    public void deleteSet(Integer setId, String userEmail) throws ResourceIsNotFoundException {

        User user = userRepository.findByEmail(userEmail);

        Set foundSet = setRepository.findByUserAndId(user, setId);
        if(foundSet == null){
            throw new ResourceIsNotFoundException("Set with id of " + setId + " is not exist");
        }

        setRepository.delete(foundSet);
    }

    @Override
    public void updateSet(Integer setId, UpdateSetRequest updateSetRequest, String userEmail) throws ResourceIsNotFoundException, AlreadyExistException {

        User user = userRepository.findByEmail(userEmail);

        Set foundSet = setRepository.findByUserAndId(user, setId);
        if(foundSet == null){
            throw new ResourceIsNotFoundException("Set with id of " + setId + " is not exist");
        }

        Set setWithTheNewName = setRepository.findByUserAndName(user, updateSetRequest.getName());
        if(setWithTheNewName != null){
            throw new AlreadyExistException("Set with name of " + setWithTheNewName.getName() + " is already exists");
        }

        beanMapperUtil.map(updateSetRequest, foundSet);
        foundSet.setUser(user);

        setRepository.save(foundSet);
    }

    @Override
    public List<SetDto> getAllSets(String userEmail) {

        User user = userRepository.findByEmail(userEmail);

        List<Set> setList = setRepository.findByUser(user);
        List<SetDto> setDtoList = beanMapperUtil.map(setList, SetDto.class);

        return setDtoList;
    }

}
