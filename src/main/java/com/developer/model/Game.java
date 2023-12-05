package com.developer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Game {
    @Id
    private String id;

    private Player player1;
    
    private Player player2;
    private GameStatus gameStatus;
    private int [][]borad;
    private TicToe winner;

}
