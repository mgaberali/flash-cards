package com.mg.flashcards.rest.controllers;

import com.mg.flashcards.dtos.CardDto;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.services.CardService;
import com.mg.flashcards.rest.handlers.ErrorMessage;
import com.mg.flashcards.rest.requests.CreateCardRequest;
import com.mg.flashcards.rest.requests.UpdateCardRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/card")
@Api(value = "/card", description = "Card CRUD operations", tags = {"Card"})
@ApiResponses({@ApiResponse(code = 400, message = "", response = ErrorMessage.class)})
public class CardController {

    @Autowired
    private CardService cardService;

    @ApiOperation(value = "Get all cards by set id")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardDto>> getAllCardsBySetId(@RequestParam("setId") Integer setId) throws ResourceIsNotFoundException {
        List<CardDto> allCards = cardService.getAllCards(setId);
        return new ResponseEntity(allCards, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new card")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCard(@Valid @RequestBody CreateCardRequest createCardRequest) throws ResourceIsNotFoundException, AlreadyExistException {
        cardService.createCard(createCardRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update card")
    @RequestMapping(value = "/{cardId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCard(@PathVariable("cardId") Integer cardId, @Valid @RequestBody UpdateCardRequest updateCardRequest) throws ResourceIsNotFoundException, AlreadyExistException {
        cardService.updateCard(cardId, updateCardRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete card")
    @RequestMapping(value = "/{cardId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCard(@PathVariable("cardId") Integer cardId) throws ResourceIsNotFoundException {
        cardService.deleteCard(cardId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
