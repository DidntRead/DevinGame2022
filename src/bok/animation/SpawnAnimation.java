package bok.animation;

import bok.engine.animations.AnimationInterface;
import org.joml.Matrix4f;

import java.awt.*;

public class SpawnAnimation implements AnimationInterface {
    private long startTime;
    private final long ANIMATIONDURATION = 1000;
    private final float JUMPHIGN = 5F;
    private double rotation;

    public SpawnAnimation(){
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void animate(Graphics2D g, int boxSize, int x, int y) {
        this.rotation = percentDone() * 2 * Math.PI;
        g.rotate(this.rotation, x + (float)boxSize / 2, y + (float)boxSize / 2);
//        g.translate(x,y);
    }

    @Override
    public void returnAnimation(Graphics2D g, int boxSize, int x, int y) {
        g.rotate(- this.rotation, x + (float)boxSize / 2, y + (float)boxSize / 2);

    }

    @Override
    public void animate3D(Matrix4f matrix4f) {
        matrix4f.scale((float) Math.pow(percentDone(), 2));
        matrix4f.m32(this.JUMPHIGN - this.JUMPHIGN *(float) Math.sqrt( percentDone()));
    }

    private double percentDone(){
        return (double) (System.currentTimeMillis() - this.startTime) / ANIMATIONDURATION;
    }

    @Override
    public boolean isWorking() {
        return System.currentTimeMillis() - this.startTime < ANIMATIONDURATION;
    }
}
