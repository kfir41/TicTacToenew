package com.developer.model;

import org.springframework.data.annotation.Transient;

import jakarta.persistence.ElementCollection;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Game {
    @Id
    private String id;

    @OneToOne
    private Player player1;
    
    @OneToOne
    private Player player2;
    private GameStatus gameStatus;
    
    @Transient
    @ElementCollection
    private int [][]borad;
    
    private TicToe winner;

}
