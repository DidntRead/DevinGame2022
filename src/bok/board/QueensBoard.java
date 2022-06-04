package bok.board;

import bok.animation.SpawnAnimation;
import bok.bot.Bot;
import bok.bot.MinMaxBot;
import bok.engine.board.interfaces.Board;
import bok.engine.game2d.Game2D;
import bok.engine.game2d.Move;
import bok.engine.game3d.Game3D;
import bok.utils.ValidatedInput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QueensBoard extends Board {
    private int player = 0;
    private Bot bot;

    public QueensBoard(List<Move> possibleMoves){
        List<Move> mv = new ArrayList<>(possibleMoves.size());
        possibleMoves.forEach((move -> mv.add(new Move(move.getX(), move.getY()))));
        this.setPossibleMoves(mv);
    }

    public QueensBoard(){
        Move boardSize = ValidatedInput.getValidatedBoardSize();
        super.initBoard(boardSize.getX(), boardSize.getY());
        this.initPossibleMoves();
        this.askToPlayVsBot();
    }

    private void askToPlayVsBot() {
        int option = JOptionPane.showOptionDialog(null, "Choose to play vs human or bot", "Playing Mode", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"PvP", "PvsBot"}, "2D");
        switch (option) {
            case 0:
                return;
            case 1:
                this.bot = new MinMaxBot();
//                this.botPlay();
                return;
            default:
                System.exit(0);

        }

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
            if (bot != null && !this.isGameWon()){
                this.botPlay();
            }
        }
    }

    private void botPlay() {
        Move move = this.bot.play(this);
        this.makeMove(move.getX(),move.getY());
    }

    @Override
    public void makeMove(int x, int y){
        Queen queen = new Queen(this, this.player%2==0? Color.GREEN: Color.RED, this.player);
        queen.setAnimation(new SpawnAnimation());
        this.setPiece(queen,x,y);
        this.removeMoves(x, y);
        if (this.getPossibleMoves().size() == 0){
            this.setWinner(this.getPlayerOnTurn());
            return;
        }
        this.changePlayer();
    }

    public void removeMoves(int x, int y) {
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
        return bot==null ? "Player" + (this.player + 1) : player==0 ? "Player" : "Bot" ;
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
