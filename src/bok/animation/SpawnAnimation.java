package bok.animation;

import bok.engine.animations.AnimationInterface;
import org.joml.Matrix4f;

import java.awt.*;

public class SpawnAnimation implements AnimationInterface {
    private final long startTime;
    private final long ANIMATIONDURATION = 1000;

    public SpawnAnimation(){
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void animate(Graphics2D g, int boxSize, int x, int y) {
        double scale = boxSize * percentDone();
        g.scale(scale, scale);
    }

    @Override
    public void returnAnimation(Graphics2D g, int boxSize, int x, int y) {
        g.scale(1, 1);
    }

    @Override
    public void animate3D(Matrix4f matrix4f) {
        matrix4f.scale((float) percentDone());

    }

    private double percentDone(){
        return (double) startTime / ANIMATIONDURATION;
    }

    @Override
    public boolean isWorking() {
        return false;
    }
}
