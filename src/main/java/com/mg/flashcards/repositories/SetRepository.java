package com.mg.flashcards.repositories;

import com.mg.flashcards.entities.Set;
import com.mg.flashcards.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetRepository extends JpaRepository<Set, Integer>{
    Set findByName(String name);
    List<Set> findByUser(User user);
    Set findByUserAndName(User user, String name);
    Set findByUserAndId(User user, Integer id);
}
