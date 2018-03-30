package com.mg.flashcards.web.requests;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class CreateCardRequest {

    @NotBlank
    @Length(min = 1, max = 100)
    private String term;

    @NotBlank
    @Length(min = 1, max = 200)
    private String definition;

    private String imageUrl;

    @NotNull
    private Integer setId;
}
