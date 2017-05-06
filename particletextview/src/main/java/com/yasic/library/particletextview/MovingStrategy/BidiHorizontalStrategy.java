package com.yasic.library.particletextview.MovingStrategy;

import com.yasic.library.particletextview.Object.Particle;

public class BidiHorizontalStrategy extends MovingStrategy {
    @Override
    public void setMovingPath(Particle particle, int rangeWidth, int rangeHeight, double[] targetPosition) {
        Double[][] path = new Double[4][2];
        path[0][0] = (Math.random() < 0.5)?Double.valueOf(-particle.getRadius()) : Double.valueOf(rangeWidth + particle.getRadius());
        path[0][1] = Math.random() * rangeHeight;
        path[1][0] = targetPosition[0];
        path[1][1] = targetPosition[1];
        path[2][0] = (Math.random() < 0.5)?Double.valueOf(-particle.getRadius()) : Double.valueOf(rangeWidth + particle.getRadius());
        path[2][1] = Math.random() * rangeHeight;
        path[3][0] = targetPosition[0];
        path[3][1] = targetPosition[1];
        particle.setPath(path);
    }
}
