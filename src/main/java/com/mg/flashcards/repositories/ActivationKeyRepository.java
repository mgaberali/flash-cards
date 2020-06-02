package com.mg.flashcards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mg.flashcards.entities.ActivationKey;

public interface ActivationKeyRepository  extends JpaRepository<ActivationKey, Integer> {

	ActivationKey findByKey(String key);
}
