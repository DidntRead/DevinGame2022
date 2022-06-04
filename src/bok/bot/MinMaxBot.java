package bok.bot;

import bok.board.QueensBoard;
import bok.engine.board.interfaces.Board;
import bok.engine.board.interfaces.Piece;
import bok.engine.game2d.Move;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinMaxBot implements Bot{

    public Move play(Board board){
       List<Move> possibleMoves = board.getPossibleMoves();
       HashMap<Move,Integer> moveValue = new HashMap<>();
        for (Move move : possibleMoves) {
            QueensBoard board1 = new QueensBoard(board.getPossibleMoves(), board.getWidth(), board.getHeight());
            board1.removeMoves(move.getX(), move.getX());
            moveValue.put(move, minimax(board1, 0, false));
        }
        Move bestMove = null;
        int bestMoveValue = -1000;
        for(Map.Entry<Move,Integer> entry: moveValue.entrySet()){
            if (bestMoveValue < entry.getValue()){
                bestMoveValue = entry.getValue();
                bestMove = entry.getKey();
            }
        }
        return bestMove;
    }

    private Piece[][] cloneBoard(Piece[][] pieces) {
        Piece [][] p = new Piece[pieces.length][];
        for(int i = 0; i < pieces.length; i++)
        {
            Piece[] aMatrix = pieces[i];
            int aLength = aMatrix.length;
            p[i] = new Piece[aLength];
            System.arraycopy(aMatrix, 0, p[i], 0, aLength);
        }
        return p;
    }

    public final int WINSCORE = 30;
    public final int LOSESCORE = -30;

    public int minimax(Board board, int depth, boolean isMaximizingPlayer){
        if (board.isGameWon()){
            return (isMaximizingPlayer ? LOSESCORE : WINSCORE) + depth;
        }

        if(depth == 5){
            return 0;
        }

        if (isMaximizingPlayer){
            int best = -1000;
            for (Move move: board.getPossibleMoves()){
                QueensBoard board1 = new QueensBoard(board.getPossibleMoves(), board.getWidth(), board.getHeight());
                board1.removeMoves(move.getX(), move.getX());
                best = Math.max(best, minimax(board1, depth+1, false));
            }
            return best;
        }else{
            int best = 1000;
            for (Move move: board.getPossibleMoves()){
                QueensBoard board1 = new QueensBoard(board.getPossibleMoves(), board.getWidth(), board.getHeight());
                board1.removeMoves(move.getX(), move.getX());
                best = Math.min(best, minimax(board1, depth+1, false));
            }
            return best;
        }

    }


//    function minimax(board, depth, isMaximizingPlayer):
//
//            if current board state is a terminal state :
//            return value of the board
//
//    if isMaximizingPlayer :
//    bestVal = -INFINITY
//        for each move in board :
//    value = minimax(board, depth+1, false)
//    bestVal = max( bestVal, value)
//        return bestVal
//
//    else :
//    bestVal = +INFINITY
//        for each move in board :
//    value = minimax(board, depth+1, true)
//    bestVal = min( bestVal, value)
//        return bestVal
}
