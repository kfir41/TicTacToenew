package com.developer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Player {
    private String login;
    @ManyToOne
    @Id
    private Game game;

}
