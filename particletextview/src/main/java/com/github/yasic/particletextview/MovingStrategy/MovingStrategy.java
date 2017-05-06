package com.github.yasic.particletextview.MovingStrategy;

import com.github.yasic.particletextview.Object.Particle;

public abstract class MovingStrategy {
    public abstract void setMovingPath(Particle particle, int rangeWidth, int rangeHeight, double[] targetPosition);
}
