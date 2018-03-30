package com.mg.flashcards.repositories;

import com.mg.flashcards.entities.Card;
import com.mg.flashcards.entities.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {


    List<Card> findBySet(Set set);
    Card findBySetAndTerm(Set set, String term);
}
