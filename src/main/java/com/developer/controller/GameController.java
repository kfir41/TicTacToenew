package com.developer.controller;

import com.developer.controller.dto.ConnectRequest;
import com.developer.exception.InvalidGameExeption;
import com.developer.exception.InvalidMove;
import com.developer.exception.InvalidParamExeption;
import com.developer.exception.NotFoundException;
import com.developer.model.Game;
import com.developer.model.GamePlay;
import com.developer.model.Player;
import com.developer.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
//   private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public ResponseEntity<Game>start(@RequestBody Player player)
    {
        log.info("start game request: {} ",player);

        return ResponseEntity.ok(gameService.createGame(player));
    }
    @PostMapping("/connect")
    public ResponseEntity<Game>connect(@RequestBody ConnectRequest request) throws InvalidParamExeption, InvalidGameExeption {
        log.info("connect request: {} ",request);
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(),request.getGameId()));
    }
    @PostMapping("/connect/random")
    public ResponseEntity<Game>connectToRandoGame(@RequestBody Player player) throws  NotFoundException {
        log.info("connect random: {} ",player);
        return ResponseEntity.ok(gameService.connectToRandomGame(player));
    }
    @PostMapping("/gamePlay")
    public ResponseEntity<Game> gamePlay(@RequestBody GamePlay request) throws NotFoundException, InvalidGameExeption, InvalidMove {
        log.info("gamePlay: {} ", request);
        Game game= gameService.gamePlay(request);
//        messagingTemplate.convertAndSend("/topic/game-progress/"+game.getId(),game);
        return ResponseEntity.ok(game);
    }
}
