package bok.bot;

import bok.board.QueensBoard;
import bok.engine.board.interfaces.Board;
import bok.engine.board.interfaces.Piece;
import bok.engine.game2d.Move;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MinMaxBot implements Bot{

    public Move play(Board board){
       List<Move> possibleMoves = board.getPossibleMoves();
       HashMap<Move,Integer> moveValue = new HashMap<>();
        for (Move move : possibleMoves) {
            QueensBoard board1 = new QueensBoard(board.getPossibleMoves());
            board1.removeMoves(move.getX(), move.getX());
            moveValue.put(move, minimax(board1, 0, false));
        }
        Optional<Map.Entry<Move, Integer>> firstMove = moveValue.entrySet().stream().findFirst();
        if (!firstMove.isPresent())
            throw new RuntimeException("No moves");
        Move bestMove = firstMove.get().getKey();
        int bestMoveValue = firstMove.get().getValue();
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
        if (board.getPossibleMoves().size() == 0){
            return (isMaximizingPlayer ? LOSESCORE + depth : WINSCORE - depth);
        }

        if(depth == 5){
            return 0;
        }

        int best;
        if (isMaximizingPlayer){
            best = -1000;
            for (Move move: board.getPossibleMoves()){
                QueensBoard board1 = new QueensBoard(board.getPossibleMoves());
                board1.removeMoves(move.getX(), move.getX());
                best = Math.max(best, minimax(board1, depth+1, !isMaximizingPlayer));
            }
        }else{
            best = 1000;
            for (Move move: board.getPossibleMoves()){
                QueensBoard board1 = new QueensBoard(board.getPossibleMoves());
                board1.removeMoves(move.getX(), move.getX());
                best = Math.min(best, minimax(board1, depth+1, !isMaximizingPlayer));
            }
        }
        return best;

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
