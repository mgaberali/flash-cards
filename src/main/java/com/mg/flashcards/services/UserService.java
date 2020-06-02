package com.mg.flashcards.services;

import com.mg.flashcards.dtos.UserDto;
import com.mg.flashcards.exceptions.AlreadyExistException;

public interface UserService {

    void signupUser(UserDto userDto) throws AlreadyExistException, Exception;

    void activateAccount(final String activationKey) throws Exception;
}
