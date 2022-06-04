package bok.bot;

import bok.board.QueensBoard;
import bok.engine.board.interfaces.Board;
import bok.engine.game2d.Move;

public interface Bot {
    Move play(Board board);
}
