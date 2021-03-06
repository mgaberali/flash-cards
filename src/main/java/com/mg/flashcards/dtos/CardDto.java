package com.mg.flashcards.dtos;

import org.dozer.Mapping;

public class CardDto {

    private Integer id;
    private String term;
    private String definition;
    private String imageUrl;
    @Mapping("set.id")
    private Integer setId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return "CardDto{" +
                "id=" + id +
                ", term='" + term + '\'' +
                ", definition='" + definition + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", setId=" + setId +
                '}';
    }
}
