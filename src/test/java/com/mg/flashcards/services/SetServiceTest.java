package com.mg.flashcards.services;

import com.mg.flashcards.FlashCardsApplication;
import com.mg.flashcards.entities.Set;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.repositories.SetRepository;
import com.mg.flashcards.web.requests.CreateSetRequest;
import com.mg.flashcards.web.requests.UpdateSetRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FlashCardsApplication.class)
@DirtiesContext
public class SetServiceTest {

    @Autowired
    private SetService setService;

    @MockBean
    private SetRepository setRepository;

    @Test(expected = AlreadyExistException.class)
    public void whenCreateSetWithAnExistingName() throws AlreadyExistException {

        // given
        Set set = createSet();
        Mockito.when(setRepository.findByName(set.getName())).thenReturn(set);

        // when
        CreateSetRequest createSetRequest = createCreateSetRequest();
        setService.createSet(createSetRequest);

    }

    @Test(expected = ResourceIsNotFoundException.class)
    public void whenDeleteSetThatNotExist() throws ResourceIsNotFoundException {

        // given
        Mockito.when(setRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));

        // when
        setService.deleteSet(1);

    }

    @Test(expected = ResourceIsNotFoundException.class)
    public void whenUpdateSetThatNotExist_thenThrowException() throws ResourceIsNotFoundException {

        // given
        Mockito.when(setRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));

        // when
        UpdateSetRequest updateSetRequest = createUpdateSetRequest();
        setService.updateSet(1 ,updateSetRequest);

    }

    private Set createSet() {
        Set set = new Set();
        set.setId(1);
        set.setName("test set");
        set.setDesc("This is a test set");
        set.setCreatedAt(new Date());
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

}
