package com.github.yasic.particletextview.Object;

public class Particle {
    private float radius;
    private String particleColor;
    private double sourceX = 0;
    private double sourceY = 0;
    private double targetX = 0;
    private double targetY = 0;
    private double vx = 0;
    private double vy = 0;

    public Particle(float radius, String particleColor) {
        this.radius = radius;
        this.particleColor = particleColor;
    }

    public void setPath(double sourceX, double sourceY, double targetX, double targetY){
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public void setSourceX(double sourceX) {
        this.sourceX = sourceX;
    }

    public void setSourceY(double sourceY) {
        this.sourceY = sourceY;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public float getRadius() {
        return radius;
    }

    public String getParticleColor() {
        return particleColor;
    }

    public double getSourceX() {
        return sourceX;
    }

    public double getSourceY() {
        return sourceY;
    }

    public double getTargetX() {
        return targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }
}
