package com.mg.flashcards.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "_set")
@Data
@ToString
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "_desc")
    private String desc;

    @Column(name = "created_at")
    private Date createdAt;

}
