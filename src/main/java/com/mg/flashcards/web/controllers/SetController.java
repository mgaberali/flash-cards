package com.mg.flashcards.web.controllers;

import com.mg.flashcards.dtos.SetDto;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.services.SetService;
import com.mg.flashcards.web.handlers.ErrorMessage;
import com.mg.flashcards.web.requests.CreateSetRequest;
import com.mg.flashcards.web.requests.UpdateCardRequest;
import com.mg.flashcards.web.requests.UpdateSetRequest;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("set")
@Api(value = "/pet", description = "Set CRUD operations", tags = {"Set"})
@ApiResponses({@ApiResponse(code = 400, message = "", response = ErrorMessage.class)})
public class SetController {

    @Autowired
    private SetService setService;

    @ApiOperation(value = "Get all sets")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SetDto>> getAllSets() {
        List<SetDto> allSets = setService.getAllSets();
        return new ResponseEntity(allSets, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new set")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addSet(@RequestBody @Valid CreateSetRequest createSetRequest) throws AlreadyExistException {
        setService.createSet(createSetRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update set")
    @RequestMapping(value = "/{setId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateSet(@PathVariable Integer setId, @RequestBody @Valid UpdateSetRequest updateSetRequest) throws ResourceIsNotFoundException {
        setService.updateSet(setId, updateSetRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete set")
    @RequestMapping(value = "/{setId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteSet(@PathVariable Integer setId) throws ResourceIsNotFoundException {
        setService.deleteSet(setId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
