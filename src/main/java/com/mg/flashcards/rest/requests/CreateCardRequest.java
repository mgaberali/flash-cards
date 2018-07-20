package com.mg.flashcards.rest.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    @Override
    public String toString() {
        return "CreateCardRequest{" +
                "term='" + term + '\'' +
                ", definition='" + definition + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", setId=" + setId +
                '}';
    }
}
