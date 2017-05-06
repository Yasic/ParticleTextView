package com.yasic.library.particletextview.MovingStrategy;

import com.yasic.library.particletextview.Object.Particle;

public abstract class MovingStrategy {
    public abstract void setMovingPath(Particle particle, int rangeWidth, int rangeHeight, double[] targetPosition);
}
