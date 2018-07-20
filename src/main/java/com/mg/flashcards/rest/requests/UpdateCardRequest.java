package com.mg.flashcards.rest.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UpdateCardRequest {

    @NotBlank
    @Length(min = 1, max = 100)
    private String term;

    @NotBlank
    @Length(min = 1, max = 200)
    private String definition;

    private String imageUrl;

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

    @Override
    public String toString() {
        return "UpdateCardRequest{" +
                "term='" + term + '\'' +
                ", definition='" + definition + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
