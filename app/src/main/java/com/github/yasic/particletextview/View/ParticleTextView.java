package com.github.yasic.particletextview.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.github.yasic.particletextview.Object.Particle;
import com.github.yasic.particletextview.Object.ParticleTextViewConfig;

import java.util.Random;

public class ParticleTextView extends View {
    private Paint textPaint;
    private Bitmap bitmap;
    private int[][] colorArray;
    private Particle[] particles = null;

    private int columnStep;
    private int rowStep;
    private double releasing = 0.1;
    private double miniDistance = 0.05;
    private String targetText = "Default";
    private int textSize = 120;
    private float particleRadius = 1;

    private boolean isAnimationStart = false;
    private boolean isAnimationStop = false;

    public ParticleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ParticleTextViewConfig tempConfig = new ParticleTextViewConfig.Builder().instance();
        setConfig(tempConfig);
    }

    private Paint initTextPaint() {
        Paint textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#3399ff"));
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);
        return textPaint;
    }

    public void setConfig(ParticleTextViewConfig config){
        if (config != null){
            this.columnStep = config.getColumnStep();
            this.rowStep = config.getRowStep();
            this.releasing = config.getReleasing();
            this.miniDistance = config.getMiniDistance();
            this.targetText = config.getTargetText();
            this.textSize = config.getTextSize();
            this.particleRadius = config.getParticleRadius();
        } else {
            Log.e("CONFIGERROR", "ParticleTextView Config is Null");
        }
    }

    public void startAnimation(){
        this.isAnimationStart = true;
    }

    public boolean isAnimationStop(){
        return this.isAnimationStop;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        textPaint = initTextPaint();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float textHeight = fontMetrics.descent - fontMetrics.ascent;
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas textCanvas = new Canvas(bitmap);
        textCanvas.drawText(targetText, centerX, centerY + textHeight / 2, textPaint);
        colorArray = new int[bitmap.getHeight()][bitmap.getWidth()];
        for (int row = 0; row < bitmap.getHeight(); row++) {
            for (int column = 0; column < bitmap.getWidth(); column++) {
                colorArray[row][column] = bitmap.getPixel(column, row);
            }
        }

        int red, green, blue;
        particles = new Particle[(colorArray.length / rowStep) * colorArray[0].length / columnStep];
        int index = 0;
        for (int i = 0; i < colorArray.length; i += rowStep) {
            for (int j = 0; j < colorArray[0].length; j += columnStep) {
                red = Color.red(colorArray[i][j]);
                green = Color.green(colorArray[i][j]);
                blue = Color.blue(colorArray[i][j]);
                if (red == 51 || green == 51 || blue == 51) {
                    particles[index] = new Particle(particleRadius, getRandomColor());
                    particles[index].setPath(Math.random() * getWidth(), Math.random() * getHeight(), j, i);
                    index++;
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boolean hasParticleNotFinish = false;
        if (!isAnimationStart){
            return;
        }
        for (Particle item: particles){
            if (item != null){
                if (item.getVx() > miniDistance || item.getVy() > miniDistance){
                    hasParticleNotFinish = true;
                } else {
                    item.setSourceX(item.getTargetX());
                    item.setSourceY(item.getTargetY());
                }
            }else {
                break;
            }
        }

        if (!hasParticleNotFinish){
            isAnimationStop = true;
            for (Particle item: particles){
                if (item != null) {
                    item.setSourceX(Math.random() * getWidth());
                    item.setSourceY(Math.random() * getHeight());
                } else {
                    break;
                }
            }
            isAnimationStop = false;
        }

        for (int i = 0; i < particles.length; i++) {
            canvas.save();
            if (particles[i] != null){
                canvas.save();
                textPaint.setColor(Color.parseColor(particles[i].getParticleColor()));
                canvas.drawCircle((int)particles[i].getSourceX(), (int)particles[i].getSourceY(), particles[i].getRadius(), textPaint);
                particles[i].setVx((particles[i].getTargetX() - particles[i].getSourceX()) * releasing);
                particles[i].setVy((particles[i].getTargetY() - particles[i].getSourceY()) * releasing);
                particles[i].setSourceX(particles[i].getSourceX() + particles[i].getVx());
                particles[i].setSourceY(particles[i].getSourceY() + particles[i].getVy());
            } else {
                canvas.restore();
                invalidate();
                break;
            }
        }
    }

    private String getRandomColor() {
        String red, green, blue;
        Random random = new Random();

        red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

        red = red.length() == 1 ? "0" + red : red ;
        green = green.length() == 1 ? "0" + green : green ;
        blue = blue.length() == 1 ? "0" + blue : blue ;
        return "#" + red + green + blue;
    }
}
