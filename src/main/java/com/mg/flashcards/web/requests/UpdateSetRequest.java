package com.mg.flashcards.web.requests;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class UpdateSetRequest {

    @NotBlank
    @Length(min = 1, max = 50)
    private String name;

    @Length(max = 100)
    private String desc;
}
