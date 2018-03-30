package com.mg.flashcards.dtos;

import lombok.Data;
import lombok.ToString;
import org.dozer.Mapping;

@Data
@ToString
public class CardDto {

    private Integer id;
    private String term;
    private String definition;
    private String imageUrl;
    @Mapping("set.id")
    private Integer setId;
}
