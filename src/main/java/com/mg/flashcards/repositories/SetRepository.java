package com.mg.flashcards.repositories;

import com.mg.flashcards.entities.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetRepository extends JpaRepository<Set, Integer>{
    Set findByName(String name);
}
