package com.mg.flashcards.rest.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UpdateSetRequest {

    @NotBlank
    @Length(min = 1, max = 50)
    private String name;

    @Length(max = 100)
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "UpdateSetRequest{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
