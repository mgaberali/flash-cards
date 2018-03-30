package com.mg.flashcards.web.requests;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateCardRequest {

    private String term;
    private String definition;
    private String imageUrl;
    private Integer setId;
}
