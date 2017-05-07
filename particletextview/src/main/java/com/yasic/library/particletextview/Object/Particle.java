package com.yasic.library.particletextview.Object;

public class Particle {
    private float radius;
    private String particleColor;
    private double sourceX = 0;
    private double sourceY = 0;
    private double targetX = 0;
    private double targetY = 0;
    private double vx = 0;
    private double vy = 0;
    private Double[][] path = null;
    private int pathProcess = 0;

    public Particle(float radius, String particleColor) {
        this.radius = radius;
        this.particleColor = particleColor;
    }

    public void updatePathProcess(){
        addPathProcess();
        setSourceX(path[pathProcess][0]);
        setSourceY(path[pathProcess][1]);
        setTargetX(path[(pathProcess + 1) % path.length][0]);
        setTargetY(path[(pathProcess + 1) % path.length][1]);
    }

    public void setSourceX(double sourceX) {
        this.sourceX = sourceX;
    }

    public void setSourceY(double sourceY) {
        this.sourceY = sourceY;
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void setParticleColor(String particleColor) {
        this.particleColor = particleColor;
    }

    public void setPath(Double[][] path){
        this.path = path;
        this.setSourceX(path[0][0]);
        this.setSourceY(path[0][1]);
        this.setTargetX(path[1][0]);
        this.setTargetY(path[1][1]);
    }

    private void addPathProcess() {
        this.pathProcess = (pathProcess + 1) % path.length;
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

    public Double[][] getPath() {
        return path;
    }

    public int getPathProcess() {
        return pathProcess;
    }
}
