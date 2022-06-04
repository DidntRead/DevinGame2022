package bok.board;

import bok.engine.board.interfaces.Board;
import bok.engine.board.interfaces.Piece;
import bok.engine.game2d.Move;
import bok.engine.game3d.util.obj.ModelData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Queen extends Piece {
    public Queen(Board board, Color color, int player) {
        super(board, color, player);
    }

    @Override
    protected BufferedImage loadImage() throws IOException {
        return null;
    }

    @Override
    protected ModelData loadModelData() {
        return null;
    }

    @Override
    public List<Move> getPossibleMoves(int i, int i1) {
        return null;
    }
}
