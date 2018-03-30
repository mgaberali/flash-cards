package com.mg.flashcards.web.requests;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ToString
public class UpdateCardRequest {

    private Integer id;
    private String term;
    private String definition;
    private String imageUrl;
}
