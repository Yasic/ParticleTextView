package com.yasic.library.particletextview.MovingStrategy;

import com.yasic.library.particletextview.Object.Particle;

public class CornerStrategy extends MovingStrategy {

    @Override
    public void setMovingPath(Particle particle, int rangeWidth, int rangeHeight, double[] targetPosition) {
        Double[][] path = new Double[4][2];
        path[0][0] = (double) ((Math.random() < 0.5) ? 0 : rangeWidth);
        if (path[0][0] == 0){
            path[0][0] -= Math.random() * rangeWidth/10;
        } else {
            path[0][0] += Math.random() * rangeWidth/10;
        }
        path[0][1] = (double) ((Math.random() < 0.5) ? 0 : rangeHeight);
        if (path[0][1] == 0){
            path[0][1] -= Math.random() * rangeHeight/10;
        } else {
            path[0][1] += Math.random() * rangeHeight/10;
        }
        path[1][0] = targetPosition[0];
        path[1][1] = targetPosition[1];
        path[2][0] = Double.valueOf(rangeWidth/2);
        path[2][1] = Double.valueOf(rangeHeight/2);
        path[3][0] = targetPosition[0];
        path[3][1] = targetPosition[1];
        particle.setPath(path);
    }
}
