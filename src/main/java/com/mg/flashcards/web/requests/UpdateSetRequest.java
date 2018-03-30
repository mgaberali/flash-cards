package com.mg.flashcards.web.requests;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateSetRequest {

    private String name;
    private String desc;
}
