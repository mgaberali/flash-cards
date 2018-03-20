package com.mg.flashcards.repositories;

import com.mg.flashcards.entities.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SetRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SetRepository setRepository;

    @Test
    public void whenFindByName_thenReturnSet(){

        // given
        Set set = createSet();
        entityManager.persist(set);
        entityManager.flush();

        // when
        Set found = setRepository.findByName(set.getName());

        // then
        assertThat(found, notNullValue());
    }


    private Set createSet() {
        Set set = new Set();
        set.setName("test set");
        set.setDesc("This is a test set");
        set.setCreatedAt(new Date());
        return set;
    }


}
