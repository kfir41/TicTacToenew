
package com.developer.service;
import com.developer.exception.InvalidGameExeption;
import com.developer.exception.InvalidMove;
import com.developer.exception.InvalidParamExeption;
import com.developer.exception.NotFoundException;
import com.developer.model.*;
//import com.developer.storage.GameStorage;
import com.developer.model.TicTacToeGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import static com.developer.model.GameStatus.*;


@Service
@AllArgsConstructor
public class GameService {
    @Autowired
    private TicTacToeGameRepository ticTacToeGameRepository;

    public Game createGame(Player player){
        Game game=new Game();
        game.setBorad(new int [3][3]);
        game.setId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setGameStatus(NEW);
       ticTacToeGameRepository.save(game);
        return game;
    }
    public Game connectToGame(Player player2,String gameId) throws InvalidParamExeption, InvalidGameExeption{
        if(!(ticTacToeGameRepository.existsById(gameId)))
        {
            throw new InvalidParamExeption("Game with provided id dosent exist");
        }
        Game game=ticTacToeGameRepository.getReferenceById(gameId);
        if(player2 !=null)
        {
            throw new InvalidGameExeption("Game is not valid any more");
        }
        game.setPlayer2(player2);

        game.setGameStatus(IN_PROGRESS);
        ticTacToeGameRepository.save(game);
        return game;
    }

    public Game connectToRandomGame(Player player2)throws NotFoundException {
        Game game = ticTacToeGameRepository.findByGameStatus(GameStatus.NEW);
        if (game == null) {
            throw new NotFoundException("Game Not found");
        }
        game.setPlayer2(player2);
        game.setGameStatus(IN_PROGRESS);
        ticTacToeGameRepository.save(game);
        return game;

    }
    public  Game gamePlay(GamePlay gamePlay)throws NotFoundException,InvalidGameExeption, InvalidMove {


        Optional<Game> optionalGame = ticTacToeGameRepository.findById(gamePlay.getGameId());
        if (!optionalGame.isPresent()) {
            throw new NotFoundException("Game not found");}

            Game game = optionalGame.get();
        if(game.getGameStatus().equals(FINISHED))
        {
            throw new InvalidGameExeption("Game is already finished");
        }
        int [][]board=game.getBorad();
        if(board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()]!=0)  // אחרי שעשיתי את הפרונט לבדוק אם אפשר בלי זה
        {
            throw new InvalidMove("this square is not available");
        }
        board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()]=gamePlay.getType().getValue();
        boolean winnerX=checkWinner(game.getBorad(), TicToe.X);
        boolean winnerY=checkWinner(game.getBorad(), TicToe.O);
        boolean draw;
        if(winnerX==false && winnerY==false) {
            draw = true;
        }
        else
        {draw=false;}
        ticTacToeGameRepository.save(game); // Save the game to the repository
        return game;
    }
    public boolean isBoardFull(Game game) {

        for (int i = 0; i <game.getBorad().length ; i++) {
            for (int j = 0; j < game.getBorad()[i].length; j++) {
                if (game.getBorad()[i][j] == ' ') {
                    return false; // Board is not full yet
                }
            }
        }
        return true;
    }

    private boolean checkWinner(int[][] board, TicToe ticToe) {
        int [] boardarray=new int[9];
        int counterIndex=0;
        for (int i=0;i<board.length;i++)
        {
            for (int j=0;j<board[i].length;j++)
            {
                boardarray[counterIndex]=board[i][j];
                counterIndex++;
            }
        }

        int [][]winCombination={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{2,5,8},{1,4,7}};
        int counter=0;
        for (int i=0;i<winCombination.length;i++)
        {
            for (int j=0;j<winCombination[i].length;j++)
            {
                if(boardarray[winCombination[i][j]]==ticToe.getValue())
                {
                    counter++;
                    if(counter==3)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}