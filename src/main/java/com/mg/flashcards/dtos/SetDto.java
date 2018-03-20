package com.mg.flashcards.dtos;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class SetDto {

    private Integer id;
    private String name;
    private String desc;
    private Date createdAt;
}
