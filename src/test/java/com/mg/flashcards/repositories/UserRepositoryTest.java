package com.mg.flashcards.repositories;

import com.mg.flashcards.entities.User;
import com.mg.flashcards.entities.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByEmail_thenReturnUser(){

        // given 1 user
        User user = createUser();
        User insertedUser = entityManager.persist(user);
        entityManager.flush();

        // when
        User foundUser = userRepository.findByEmail(user.getEmail());

        // then
        assertThat(foundUser, notNullValue());
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
