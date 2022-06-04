package bok.board;

import bok.animation.SpawnAnimation;
import bok.bot.Bot;
import bok.bot.MinMaxBot;
import bok.engine.board.interfaces.Board;
import bok.engine.game2d.Move;
import bok.utils.ValidatedInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QueensBoard extends Board {
    private int player = 0;
    private Bot bot;

    public QueensBoard(){

        Move boardSize = ValidatedInput.getValidatedBoardSize();
        this.askToPlayVsBot();
        super.initBoard(boardSize.getX(), boardSize.getY());
        this.initPossibleMoves();
    }

    private void askToPlayVsBot() {
        this.bot = new MinMaxBot();
    }

    private void initPossibleMoves() {
        List<Move> possibleMoves = new ArrayList<>();

        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                possibleMoves.add(new Move(i,j));
            }
        }
        this.setPossibleMoves(possibleMoves);
    }


    @Override
    public void handleInput(int x, int y) {
        if (this.getPossibleMoves().contains(new Move(x,y))){
            this.makeMove(x,y);
            if (bot != null){
                this.botPlay();
            }
        }else{
            System.out.println("Wrong fdsa");
        }
    }

    private void botPlay() {
        Move move = this.bot.play(this);
        this.makeMove(move.getX(),move.getY());
    }

    private void makeMove(int x, int y){
        Queen queen = new Queen(this, this.player%2==0? Color.GREEN: Color.RED, this.player);
        queen.setAnimation(new SpawnAnimation());
        this.setPiece(queen,x,y);
        this.removeMoves(x, y);
    if (this.getPossibleMoves().size() == 0){
            this.setWinner("Player " + (this.player + 1));
            return;
        }
        this.changePlayer();
    }

    private void removeMoves(int x, int y) {
        List<Move> moves = this.getPossibleMoves().stream().filter(move -> {
           return move.getX() != x && move.getY() != y && Math.abs(move.getX() - x) != Math.abs(move.getY() - y);
        }).collect(Collectors.toList());
        this.setPossibleMoves(moves);
    }

    @Override
    public void changePlayer() {
        this.player = (this.player + 1) % 2;
    }

    @Override
    public String getPlayerOnTurn() {
        return "Player" + (this.player + 1);
    }

    @Override
    public String getGameInstructions() {

        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append("Queens:\n");
        builder.append("Queens is a game played on a MxN sized board from 2 players.\n");
        builder.append("Taking turns each player decides where to place a new queen,\n");
        builder.append("the loser is the player who is on turn, but doesn't have any possible moves\n");

        return builder.toString();
    }
}
