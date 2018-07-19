package com.mg.flashcards.repositories;

import com.mg.flashcards.entities.Card;
import com.mg.flashcards.entities.Set;
import com.mg.flashcards.entities.User;
import com.mg.flashcards.entities.UserRole;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CardRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CardRepository cardRepository;

    @Test
    public void whenFindBySet_thenReturnListOfCards(){

        // given 3 cards that belong to one set
        User user = createUser();
        entityManager.persist(user);
        Set set = createSet(user);
        Set insertedSet = entityManager.persist(set);
        List<Card> cards = createListOfCards(insertedSet);
        cards.stream().forEach(card -> entityManager.persist(card));
        entityManager.flush();

        // when
        List<Card> foundCards = cardRepository.findBySet(insertedSet);

        // then
        assertThat(foundCards.size(), equalTo(3));
    }

    @Test
    public void whenFindBySetAndTerm_thenReturnCard(){

        // given
        User user = createUser();
        entityManager.persist(user);
        Set set = createSet(user);
        Set insertedSet = entityManager.persist(set);
        Card card = createCard(insertedSet);
        Card insertedCard = entityManager.persist(card);

        // when
        Card found = cardRepository.findBySetAndTerm(insertedSet, card.getTerm());

        // then
        assertThat(found, notNullValue());
    }

    private List<Card> createListOfCards(Set set) {
        List<Card> cards = new ArrayList<>();
        for(int i = 1; i <= 3; i++){
            Card card = new Card();
            card.setTerm("term " + i);
            card.setDefinition("definition of term " + i);
            card.setCreatedAt(new Date());
            card.setSet(set);
            cards.add(card);
        }
        return cards;
    }

    private Card createCard(Set set){
        Card card = new Card();
        card.setTerm("test term 1" );
        card.setDefinition("definition of test term 1");
        card.setCreatedAt(new Date());
        card.setSet(set);
        return  card;
    }

    private Set createSet(User user) {
        Set set = new Set();
        set.setName("test set");
        set.setDesc("This is a test set");
        set.setCreatedAt(new Date());
        set.setUser(user);
        return set;
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
