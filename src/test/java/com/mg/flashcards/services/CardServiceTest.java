package com.mg.flashcards.services;

import com.mg.flashcards.FlashCardsApplication;
import com.mg.flashcards.dtos.CardDto;
import com.mg.flashcards.entities.Card;
import com.mg.flashcards.entities.Set;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.repositories.CardRepository;
import com.mg.flashcards.repositories.SetRepository;
import com.mg.flashcards.web.requests.CreateCardRequest;
import com.mg.flashcards.web.requests.UpdateCardRequest;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FlashCardsApplication.class)
@DirtiesContext
public class CardServiceTest {

    @Autowired
    private CardService cardService;

    @MockBean
    private CardRepository cardRepository;

    @MockBean
    private SetRepository setRepository;

    @Test(expected = AlreadyExistException.class)
    public void whenCreateCardWithAnExistingTerm() throws ResourceIsNotFoundException, AlreadyExistException {

        // given
        Set set = createSet(1, "test set", "test set desc");
        Card card = createCard(1, "test term1", "some def", set);
        Mockito.when(setRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(set));
        Mockito.when(cardRepository.findBySetAndTerm(Mockito.any(), Mockito.anyString())).thenReturn(card);

        // when
        CreateCardRequest createCardRequest = createCreateCardRequest(1, "term1", "term1 def");
        cardService.createCard(createCardRequest);
    }

    @Test(expected = ResourceIsNotFoundException.class)
    public void whenCreateCardWithNonExistingSet() throws ResourceIsNotFoundException, AlreadyExistException {

        // given
        Mockito.when(setRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));

        // when
        CreateCardRequest createCardRequest = createCreateCardRequest(1, "term1", "term1 def");
        cardService.createCard(createCardRequest);
    }

    @Test(expected = AlreadyExistException.class)
    public void whenUpdateCardWithAnExistingTerm() throws AlreadyExistException, ResourceIsNotFoundException {

        // given
        Set set = createSet(1, "test set", "test set desc");
        Card card = createCard(1, "term1", "some def", set);
        Card card2 = createCard(2, "term2", "term2 def", set);
        Mockito.when(cardRepository.findById(card2.getId())).thenReturn(Optional.of(card2));
        Mockito.when(cardRepository.findBySetAndTerm(set, card.getTerm())).thenReturn(card);

        // when
        UpdateCardRequest updateCardRequest = createUpdateCardRequest("term1", "term1 def");
        cardService.updateCard(card2.getId(), updateCardRequest);
    }

    @Test(expected = ResourceIsNotFoundException.class)
    public void whenUpdateCardThatIsNotExist() throws ResourceIsNotFoundException, AlreadyExistException {

        // given
        Mockito.when(cardRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));

        // when
        UpdateCardRequest updateCardRequest = createUpdateCardRequest("term 1", "term 1 def");
        cardService.updateCard(1, updateCardRequest);
    }

    @Test(expected = ResourceIsNotFoundException.class)
    public void whenDeleteCardThatIsNotExist() throws ResourceIsNotFoundException, AlreadyExistException {

        // given
        Mockito.when(cardRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));

        // when
        cardService.deleteCard(1);
    }

    @Test(expected = ResourceIsNotFoundException.class)
    public void whenGetAllCardsInNotExistedSet() throws ResourceIsNotFoundException {

        // given
        Mockito.when(setRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));

        // when
        List<CardDto> allCards = cardService.getAllCards(1);

    }

    @Test
    public void whenGetAllCardsInSet_thenReturnAllCards() throws ResourceIsNotFoundException {

        // given
        Set set = createSet(1, "set1", "set1 desc");
        Card card1 = createCard(1, "term1", "term1 def", set);
        Card card2 = createCard(2, "term2", "term2 def", set);
        List<Card> cardList = Arrays.asList(card1, card2);
        Mockito.when(setRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(set));
        Mockito.when(cardRepository.findBySet(set)).thenReturn(cardList);

        // when
        List<CardDto> cardDtoList = cardService.getAllCards(set.getId());

        // then
        Assert.assertThat(cardDtoList.size(), Matchers.equalTo(cardList.size()));
    }

    private Set createSet(Integer setId, String name, String desc) {
        Set set = new Set();
        set.setId(setId);
        set.setName(name);
        set.setDesc(desc);
        set.setCreatedAt(new Date());
        return set;
    }

    private CreateCardRequest createCreateCardRequest(Integer setId, String term, String def) {
        CreateCardRequest createCardRequest = new CreateCardRequest();
        createCardRequest.setSetId(setId);
        createCardRequest.setTerm(term);
        createCardRequest.setDefinition(def);
        return createCardRequest;
    }

    private UpdateCardRequest createUpdateCardRequest(String term, String def) {
        UpdateCardRequest updateCardRequest = new UpdateCardRequest();
        updateCardRequest.setTerm(term);
        updateCardRequest.setDefinition(def);
        return updateCardRequest;
    }

    private Card createCard(Integer id, String term, String def, Set set) {
        Card card = new Card();
        card.setId(id);
        card.setTerm(term);
        card.setDefinition(def);
        card.setCreatedAt(new Date());
        card.setSet(set);
        return card;
    }

}
