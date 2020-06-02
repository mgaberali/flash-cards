package com.mg.flashcards.rest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mg.flashcards.dtos.UserDto;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.rest.handlers.ErrorMessage;
import com.mg.flashcards.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/users")
@Api(value = "/users", description = "users signup", tags = { "signup" })
@ApiResponses({ @ApiResponse(code = 400, message = "", response = ErrorMessage.class) })
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "signup as a new user")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addUser(@Valid @RequestBody UserDto userDto)
			throws ResourceIsNotFoundException, AlreadyExistException, Exception {
		userService.signupUser(userDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Activate the user by activation key sent by mail", code = 204)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "the user activated successfully") })
	@RequestMapping(value = "/activate/{activateKey}", method = RequestMethod.GET)
	public ResponseEntity activateAccount(@PathVariable(value = "activateKey") final String activateKey)
			throws Exception {
		userService.activateAccount(activateKey);
		return new ResponseEntity("User acivated successfully", HttpStatus.NO_CONTENT);
	}

}
