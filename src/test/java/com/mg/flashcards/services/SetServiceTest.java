package com.mg.flashcards.services;

import com.mg.flashcards.entities.Set;
import com.mg.flashcards.entities.User;
import com.mg.flashcards.entities.UserRole;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.repositories.SetRepository;
import com.mg.flashcards.repositories.UserRepository;
import com.mg.flashcards.web.requests.CreateSetRequest;
import com.mg.flashcards.web.requests.UpdateSetRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class SetServiceTest {

    @Autowired
    private SetService setService;

    @MockBean
    private SetRepository setRepository;

    @MockBean
    private UserRepository userRepository;

    @Test(expected = AlreadyExistException.class)
    public void whenCreateSetWithAnExistingName() throws AlreadyExistException {

        // given
        User user = createUser();
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Set set = createSet(user);
        Mockito.when(setRepository.findByUserAndName(user, set.getName())).thenReturn(set);

        // when
        CreateSetRequest createSetRequest = createCreateSetRequest();
        setService.createSet(createSetRequest, user.getEmail());

    }

    @Test(expected = ResourceIsNotFoundException.class)
    public void whenDeleteSetThatNotExist() throws ResourceIsNotFoundException {

        // given
        User user = createUser();
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(setRepository.findByUserAndId(user, 1)).thenReturn(null);

        // when
        setService.deleteSet(1, user.getEmail());

    }

    @Test(expected = ResourceIsNotFoundException.class)
    public void whenUpdateSetThatNotExist() throws ResourceIsNotFoundException, AlreadyExistException {

        // given
        User user = createUser();
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(setRepository.findByUserAndId(user, 1)).thenReturn(null);

        // when
        UpdateSetRequest updateSetRequest = createUpdateSetRequest();
        setService.updateSet(1 ,updateSetRequest, user.getEmail());

    }

    @Test(expected = AlreadyExistException.class)
    public void whenUpdateSetWithNameIsAlreadyExisted() throws AlreadyExistException, ResourceIsNotFoundException {

        // given
        String commonSetName = "testSet99";
        User user = createUser();
        Set set = createSet(user);
        Set anotherSet = createSet(user);
        anotherSet.setName(commonSetName);
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(setRepository.findByUserAndId(user, 1)).thenReturn(set);
        Mockito.when(setRepository.findByUserAndName(user, commonSetName)).thenReturn(anotherSet);

        // when
        UpdateSetRequest updateSetRequest = createUpdateSetRequest();
        updateSetRequest.setName(commonSetName);
        setService.updateSet(1 ,updateSetRequest, user.getEmail());

    }

    private Set createSet(User user) {
        Set set = new Set();
        set.setId(1);
        set.setName("test set");
        set.setDesc("This is a test set");
        set.setCreatedAt(new Date());
        set.setUser(user);
        return set;
    }

    private CreateSetRequest createCreateSetRequest() {
        CreateSetRequest createSetRequest = new CreateSetRequest();
        createSetRequest.setName("test set");
        createSetRequest.setDesc("another desc");
        return createSetRequest;
    }

    private UpdateSetRequest createUpdateSetRequest() {
        UpdateSetRequest updateSetRequest = new UpdateSetRequest();
        updateSetRequest.setName("test set");
        updateSetRequest.setDesc("another desc");
        return updateSetRequest;
    }

    private User createUser() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("123456");
        user.setEnabled(true);

        UserRole userRole = new UserRole();
        userRole.setRole("user");
        userRole.setUser(user);

        user.setUserRoles(Collections.singletonList(userRole));

        return user;
    }


}
