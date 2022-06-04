package bok.animation;

import bok.engine.animations.AnimationInterface;
import org.joml.Matrix4f;

import java.awt.*;

public class SpawnAnimation implements AnimationInterface {
    private long startTime;
    private final long ANIMATIONDURATION = 10000;
    private final float JUMPHIGN = 2F;
    private double scale;

    public SpawnAnimation(){
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void animate(Graphics2D g, int boxSize, int x, int y) {
        this.scale = percentDone();
        g.rotate(this.scale * Math.PI, x + (float)boxSize / 2, y + (float)boxSize / 2);
//        g.translate(x,y);
    }

    @Override
    public void returnAnimation(Graphics2D g, int boxSize, int x, int y) {
        g.rotate(- this.scale * Math.PI, x + (float)boxSize / 2, y + (float)boxSize / 2);

    }

    @Override
    public void animate3D(Matrix4f matrix4f) {
        matrix4f.scale((float) Math.pow(percentDone(),2));
        matrix4f.m32(this.JUMPHIGN - (float) Math.sqrt(this.JUMPHIGN * percentDone()));
    }

    private double percentDone(){
        return (double) (System.currentTimeMillis() - this.startTime) / ANIMATIONDURATION;
    }

    @Override
    public boolean isWorking() {
        return System.currentTimeMillis() - this.startTime < ANIMATIONDURATION;
    }
}
