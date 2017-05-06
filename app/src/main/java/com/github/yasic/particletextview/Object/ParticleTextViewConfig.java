package com.github.yasic.particletextview.Object;

import android.util.Log;

import com.github.yasic.particletextview.MovingStrategy.MovingStrategy;
import com.github.yasic.particletextview.MovingStrategy.RandomMovingStrategy;

public class ParticleTextViewConfig {
    private int columnStep = 3;
    private int rowStep = 3;
    private double releasing = 0.1;
    private double miniJudgeDistance = 0.05;
    private String targetText = "Default";
    private int textSize = 120;
    private float particleRadius = 1;
    private String[] particleColorArray = null;
    private MovingStrategy movingStrategy = new RandomMovingStrategy();
    private long delay = 1000;

    private ParticleTextViewConfig(){}

    public static class Builder {
        private ParticleTextViewConfig particleTextViewConfig = null;
        public Builder(){
            this.particleTextViewConfig = new ParticleTextViewConfig();
        }

        public Builder setColumnStep(int columnStep){
            particleTextViewConfig.columnStep = columnStep;
            return this;
        }

        public Builder setRowStep(int rowStep){
            particleTextViewConfig.rowStep = rowStep;
            return this;
        }

        public Builder setReleasing(double releasing) {
            particleTextViewConfig.releasing = releasing;
            return this;
        }

        public Builder setMiniDistance(double miniDistance) {
            particleTextViewConfig.miniJudgeDistance = miniDistance;
            return this;
        }

        public Builder setTargetText(String targetText) {
            particleTextViewConfig.targetText = targetText;
            return this;
        }

        public Builder setTextSize(int textSize) {
            particleTextViewConfig.textSize = textSize;
            return this;
        }

        public Builder setParticleRadius(float radius){
            particleTextViewConfig.particleRadius = radius;
            return this;
        }

        public Builder setParticleColorArray(String[] particleColorArray){
            particleTextViewConfig.particleColorArray = particleColorArray;
            return this;
        }

        public Builder setMovingStrategy(MovingStrategy movingStrategy){
            particleTextViewConfig.movingStrategy = movingStrategy;
            return this;
        }

        public Builder setDelay(Long delay){
            particleTextViewConfig.delay = delay;
            return this;
        }

        public ParticleTextViewConfig instance(){
            return particleTextViewConfig;
        }
    }

    public int getColumnStep() {
        return columnStep;
    }

    public int getRowStep() {
        return rowStep;
    }

    public double getReleasing() {
        return releasing;
    }

    public double getMiniJudgeDistance() {
        return miniJudgeDistance;
    }

    public String getTargetText() {
        return targetText;
    }

    public int getTextSize() {
        return textSize;
    }

    public float getParticleRadius() {
        return particleRadius;
    }

    public String[] getParticleColorArray() {
        return particleColorArray;
    }

    public MovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    public long getDelay() {
        return delay;
    }
}
