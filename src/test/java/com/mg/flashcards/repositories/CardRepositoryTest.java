package com.mg.flashcards.repositories;

import com.mg.flashcards.entities.Card;
import com.mg.flashcards.entities.Set;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertThat;

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
        Set set = createSet();
        Set insertedSet = entityManager.persist(set);
        List<Card> cards = createListOfCards(insertedSet);
        cards.stream().forEach(card -> entityManager.persist(card));
        entityManager.flush();

        // when
        List<Card> foundCards = cardRepository.findBySet(insertedSet);

        // then
        assertThat(foundCards.size(), equalTo(3));
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

    private Set createSet() {
        Set set = new Set();
        set.setName("test set");
        set.setDesc("This is a test set");
        set.setCreatedAt(new Date());
        return set;
    }

}
