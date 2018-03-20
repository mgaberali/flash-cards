package com.mg.flashcards.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "card")
@Data
public class Card {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "term")
    private String term;

    @Column(name = "definition")
    private String definition;

    @Column(name = "create_at")
    private Date createdAt;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "set_id")
    private Set set;
}
