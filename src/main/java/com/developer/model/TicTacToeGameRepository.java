package com.developer.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicTacToeGameRepository extends JpaRepository<Game, String> {
    Game findByGameStatus(GameStatus gameStatus);
    Optional<Game> findById(String id);

}
