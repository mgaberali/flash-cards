package com.mg.flashcards.repositories;

import com.mg.flashcards.entities.Set;
import com.mg.flashcards.entities.User;
import com.mg.flashcards.entities.UserRole;
import org.junit.Before;
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
public class SetRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SetRepository setRepository;

    @Before
    public void clearDB(){
        setRepository.deleteAll();
    }

    @Test
    public void whenFindByName_thenReturnSet(){

        // given
        User user = createUser();
        entityManager.persist(user);
        Set set = createSet("test set", "This is a test set", user);
        entityManager.persist(set);
        entityManager.flush();

        // when
        Set found = setRepository.findByName(set.getName());

        // then
        assertThat(found, notNullValue());
    }

    @Test
    public void whenFindByUserAndName_thenReturnSet(){

        // given
        User user = createUser();
        entityManager.persist(user);
        Set set = createSet("test set", "This is a test set", user);
        entityManager.persist(set);
        entityManager.flush();

        // when
        Set found = setRepository.findByUserAndName(user, set.getName());

        // then
        assertThat(found, notNullValue());
    }

    @Test
    public void whenFindByUserAndId_thenReturnSet(){

        // given
        User user = createUser();
        entityManager.persist(user);
        Set set = createSet("test set", "This is a test set", user);
        Set persistedSet = entityManager.persist(set);
        entityManager.flush();

        // when
        Set found = setRepository.findByUserAndId(user, persistedSet.getId());

        // then
        assertThat(found, notNullValue());
    }

    @Test
    public void whenFindByUser_thenReturnListOfSets(){

        // given
        User user = createUser();
        entityManager.persist(user);
        List<Set> sets = new ArrayList<>();
        sets.add(createSet("set1", "set1 desc", user));
        sets.add(createSet("set2", "set2 desc", user));
        sets.stream().forEach(set -> entityManager.persist(set));
        entityManager.flush();

        // when
        List<Set> foundSets = setRepository.findByUser(user);

        // then
        assertThat(foundSets.size(), equalTo(2));
    }


    private Set createSet(String name, String desc, User user) {
        Set set = new Set();
        set.setName(name);
        set.setDesc(desc);
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
