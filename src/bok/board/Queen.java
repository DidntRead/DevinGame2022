package bok.board;

import bok.engine.board.interfaces.Board;
import bok.engine.board.interfaces.Piece;
import bok.engine.game2d.Move;
import bok.engine.game3d.util.obj.ModelData;
import bok.engine.game3d.util.obj.OBJFileLoader;
import bok.engine.game3d.util.obj.OBJLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Queen extends Piece {
    public Queen(Board board, Color color, int player) {
        super(board, color, player);
        getForm().setScale(0.5f);
        getForm().setRotation((float) Math.toRadians(90), 0, 0);
    }

    @Override
    protected BufferedImage loadImage() throws IOException {
        return ImageIO.read(Objects.requireNonNull(Queen.class.getResourceAsStream("/black-queen.png")));
    }

    @Override
    protected ModelData loadModelData() {
        return OBJFileLoader.loadOBJ("/pawn.obj");
    }

    @Override
    public List<Move> getPossibleMoves(int i, int i1) {
        return null;
    }
}
