package com.mg.flashcards.rest.controllers;

import com.mg.flashcards.dtos.SetDto;
import com.mg.flashcards.exceptions.AlreadyExistException;
import com.mg.flashcards.exceptions.ResourceIsNotFoundException;
import com.mg.flashcards.services.SetService;
import com.mg.flashcards.rest.handlers.ErrorMessage;
import com.mg.flashcards.rest.requests.CreateSetRequest;
import com.mg.flashcards.rest.requests.UpdateSetRequest;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/set")
@Api(value = "/set", description = "Set CRUD operations", tags = {"Set"})
@ApiResponses({@ApiResponse(code = 400, message = "", response = ErrorMessage.class)})
public class SetController {

    @Autowired
    private SetService setService;

    @ApiOperation(value = "Get all sets")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SetDto>> getAllSets(Principal principal) {
        String userEmail = principal.getName();
        List<SetDto> allSets = setService.getAllSets(userEmail);
        return new ResponseEntity(allSets, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new set")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addSet(@RequestBody @Valid CreateSetRequest createSetRequest, Principal principal) throws AlreadyExistException {
        String userEmail = principal.getName();
        Integer id = setService.createSet(createSetRequest, userEmail);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update set")
    @RequestMapping(value = "/{setId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateSet(@PathVariable Integer setId, @RequestBody @Valid UpdateSetRequest updateSetRequest, Principal principal) throws ResourceIsNotFoundException, AlreadyExistException {
        String userEmail = principal.getName();
        setService.updateSet(setId, updateSetRequest, userEmail);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete set")
    @RequestMapping(value = "/{setId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteSet(@PathVariable Integer setId, Principal principal) throws ResourceIsNotFoundException {
        String userEmail = principal.getName();
        setService.deleteSet(setId, userEmail);
        return new ResponseEntity(HttpStatus.OK);
    }

}
