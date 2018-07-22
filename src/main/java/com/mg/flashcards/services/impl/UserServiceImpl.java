package com.mg.flashcards.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mg.flashcards.config.FlashCardsConfigs;
import com.mg.flashcards.dtos.UserDto;
import com.mg.flashcards.entities.ActivationKey;
import com.mg.flashcards.entities.User;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.repositories.ActivationKeyRepository;
import com.mg.flashcards.repositories.UserRepository;
import com.mg.flashcards.services.EmailService;
import com.mg.flashcards.services.UserService;
import com.mg.flashcards.utils.BeanMapperUtil;

@Service
public class UserServiceImpl implements UserService{

    private final String WELCOME_EMAIL_SUBJECT = "Welcome! confirm your email";

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private BeanMapperUtil beanMapperUtil;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private ActivationKeyRepository activationKeyRepository;
	
	@Autowired
	private FlashCardsConfigs flashCardsConfigs;
	
	@Override
	public void signupUser(UserDto userDto) throws Exception {
		User userFromDataStore = userRepository.findByEmail(userDto.getEmail());
		if(userFromDataStore != null) {
			throw new AlreadyExistException("there is a user with this email");
		}
		
		User userToBeSaved = beanMapperUtil.map(userDto, User.class);
		userToBeSaved.setEnabled(false);
		userToBeSaved.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User savedUser = userRepository.save(userToBeSaved);

		// generate UUID for the new created user
        final String userActivationKey = UUID.randomUUID().toString();
        ActivationKey activationKey = new ActivationKey();
        activationKey.setKey(userActivationKey);
        activationKey.setUser(savedUser);

        //save activation key
        final ActivationKey createdActivateKey = activationKeyRepository.save(activationKey);

        //send email
        // send mail to activate the account
        if (createdActivateKey.getId() != null) {
            try {
				emailService.send(savedUser.getEmail(), WELCOME_EMAIL_SUBJECT, buildVerificationMail(createdActivateKey.getKey()));
			} catch (Exception e) {
            	e.printStackTrace();
				throw new Exception("there is error while sending the Activation mail");
			}
        }
		
	}

	
    private String buildVerificationMail(final String uuid) {
        final StringBuilder textBiulder = new StringBuilder();
        textBiulder.append(flashCardsConfigs.getMessage());
        textBiulder.append(flashCardsConfigs.getHostName());
        textBiulder.append("activate/" + uuid + "\n");
        textBiulder.append(flashCardsConfigs.getMessageFooter());

        return textBiulder.toString();
    }


	@Override
	public void activateAccount(final String activationKey) throws Exception {
		ActivationKey activationKeyFromDB = activationKeyRepository.findByKey(activationKey);
		User user = userRepository.findByEmail(activationKeyFromDB.getUser().getEmail());
		user.setEnabled(true);
		userRepository.save(user);
	}
}
