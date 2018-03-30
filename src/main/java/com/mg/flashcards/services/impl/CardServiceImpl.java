package com.mg.flashcards.services.impl;

import com.mg.flashcards.dtos.CardDto;
import com.mg.flashcards.entities.Card;
import com.mg.flashcards.entities.Set;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.repositories.CardRepository;
import com.mg.flashcards.repositories.SetRepository;
import com.mg.flashcards.services.CardService;
import com.mg.flashcards.utils.BeanMapperUtil;
import com.mg.flashcards.web.requests.CreateCardRequest;
import com.mg.flashcards.web.requests.UpdateCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService{

    @Autowired
    public CardRepository cardRepository;

    @Autowired
    public SetRepository setRepository;

    @Autowired
    public BeanMapperUtil beanMapperUtil;

    @Override
    public void createCard(CreateCardRequest createCardRequest) throws AlreadyExistException, ResourceIsNotFoundException {

        Optional<Set> foundSet = setRepository.findById(createCardRequest.getSetId());
        if(!foundSet.isPresent()){
            throw new ResourceIsNotFoundException("There is no set with id " + createCardRequest.getSetId());
        }

        Card foundCard = cardRepository.findBySetAndTerm(foundSet.get(), createCardRequest.getTerm());
        if(foundCard != null){
            throw new AlreadyExistException("There is already a card with the same term '" + createCardRequest.getTerm() + "'");
        }

        Card card = beanMapperUtil.map(createCardRequest, Card.class);
        card.setSet(foundSet.get());
        card.setCreatedAt(new Date());

        cardRepository.save(card);
    }

    @Override
    public void updateCard(UpdateCardRequest updateCardRequest) throws AlreadyExistException, ResourceIsNotFoundException {

        Optional<Card> foundCard = cardRepository.findById(updateCardRequest.getId());
        if(!foundCard.isPresent()){
            throw new ResourceIsNotFoundException("There is no card with id " + updateCardRequest.getId());
        }

        Card anotherCardWithSameTerm = cardRepository.findBySetAndTerm(foundCard.get().getSet(), updateCardRequest.getTerm());
        if (anotherCardWithSameTerm != null && anotherCardWithSameTerm.getId() != foundCard.get().getId()){
            throw new AlreadyExistException("There is already a card with the same term '" + updateCardRequest.getTerm() + "'");
        }

        Card card = foundCard.get();
        beanMapperUtil.map(updateCardRequest, card);

        cardRepository.save(card);
    }

    @Override
    public void deleteCard(Integer cardId) throws ResourceIsNotFoundException {

        Optional<Card> foundCard = cardRepository.findById(cardId);
        if(!foundCard.isPresent()){
            throw new ResourceIsNotFoundException("There is no card with id " + cardId);
        }

        cardRepository.delete(foundCard.get());
    }

    @Override
    public List<CardDto> getAllCards(Integer setId) throws ResourceIsNotFoundException {

        Optional<Set> foundSet = setRepository.findById(setId);
        if(!foundSet.isPresent()){
            throw new ResourceIsNotFoundException("There is no set with id " + setId);
        }

        List<Card> cardList = cardRepository.findBySet(foundSet.get());

        List<CardDto> cardDtoList = beanMapperUtil.map(cardList, CardDto.class);

        return cardDtoList;
    }
}
