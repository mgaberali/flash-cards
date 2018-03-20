package com.mg.flashcards.web.requests;

import lombok.Data;

@Data
public class CreateSetRequest {

    private String name;
    private String desc;
}
