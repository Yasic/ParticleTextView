package com.yasic.library.particletextview.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yasic.library.particletextview.MovingStrategy.MovingStrategy;
import com.yasic.library.particletextview.Object.Particle;
import com.yasic.library.particletextview.Object.ParticleTextViewConfig;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ParticleTextView extends View {
    private Paint textPaint;
    private Bitmap bitmap;
    private Particle[] particles = null;
    private boolean isAnimationOn = false;
    private boolean isAnimationPause = false;
    private boolean isAnimationFrozen = false;
    private boolean isAnimationStop = false;
    private boolean viewUpdated = false;
    private boolean setParticles = true;

    private int columnStep;
    private int rowStep;
    private double releasing;
    private double miniJudgeDistance;
    private String targetText = null;
    private String[] targetTextArray = null;
    private int textSize;
    private float particleRadius;
    private String[] particleColorArray = null;
    private MovingStrategy movingStrategy = null;
    private long delay = 1000;
    private long delayHolder = 1000;
    private int textIterator = 0;
    private int xPosition;
    private int yPosition;

    public ParticleTextView(Context context) {
        super(context);
        ParticleTextViewConfig tempConfig = new ParticleTextViewConfig.Builder().instance();
        setConfig(tempConfig);
    }

    public ParticleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ParticleTextViewConfig tempConfig = new ParticleTextViewConfig.Builder().instance();
        setConfig(tempConfig);
    }

    private Paint initTextPaint() {
        Paint textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#3399ff"));
        textPaint.setAntiAlias(false);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);
        return textPaint;
    }

    public void setConfig(ParticleTextViewConfig config) {
        if (config != null) {
            this.columnStep = config.getColumnStep();
            this.rowStep = config.getRowStep();
            this.releasing = config.getReleasing();
            this.miniJudgeDistance = config.getMiniJudgeDistance();
            this.targetText = config.getTargetText();
            this.targetTextArray = config.getTargetTextArray();
            this.textSize = config.getTextSize();
            this.particleRadius = config.getParticleRadius();
            this.particleColorArray = config.getParticleColorArray();
            this.movingStrategy = config.getMovingStrategy();
            this.delay = config.getDelay();
            this.delayHolder = config.getDelay();
            this.xPosition = config.getXPosition();
            this.yPosition = config.getYPosition();
        } else {
            Log.e("CONFIGERROR", "ParticleTextView Config is Null");
        }
    }

    public void startAnimation() {
        this.isAnimationOn = true;
        this.isAnimationFrozen = false;
        setParticles = false;
        invalidate();
    }

    public void stopAnimation() {
        this.isAnimationOn = false;
        this.textIterator = 0;
    }

    public boolean isAnimationPause() {
        return this.isAnimationPause;
    }

    public boolean isAnimationStop(){
        return this.isAnimationStop;
    }

    private int[][] bitmapTransition() {
        int centerX = (int) (((float) getWidth() / 100) * xPosition);
        int centerY = (int) (((float) getHeight() / 100) * yPosition);
        int[][] colorArray;
        textPaint = initTextPaint();
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas textCanvas = new Canvas(bitmap);
        textCanvas.drawText(targetText, centerX, centerY, textPaint);
        colorArray = new int[bitmap.getHeight()][bitmap.getWidth()];
        for (int row = 0; row < bitmap.getHeight(); row++) {
            for (int column = 0; column < bitmap.getWidth(); column++) {
                colorArray[row][column] = bitmap.getPixel(column, row);
            }
        }
        return colorArray;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!setParticles)
        {
            if (targetTextArray != null)
            {
                //Iterate through text array loop to show all text values.
                if (textIterator == targetTextArray.length)
                {
                    textIterator = 0;
                    targetText = targetTextArray[textIterator];
                }
                else
                {
                    targetText = targetTextArray[textIterator];
                    textIterator += 1;
                }
            }
            setParticles();
            setParticles = true;
        }

        if (!isAnimationOn) {
            return;
        }

        if (!checkJudgeDistance()) {
            pauseAnimation();
        }

        if (!isAnimationFrozen)
        {
            for (int i = 0; i < particles.length; i++) {
                canvas.save();
                if (particles[i] != null) {
                    textPaint.setColor(Color.parseColor(particles[i].getParticleColor()));
                    canvas.drawCircle((int) particles[i].getSourceX(), (int) particles[i].getSourceY(), particles[i].getRadius(), textPaint);
                    particles[i].setVx((particles[i].getTargetX() - particles[i].getSourceX()) * releasing);
                    particles[i].setVy((particles[i].getTargetY() - particles[i].getSourceY()) * releasing);
                    if (!isAnimationPause) {
                        particles[i].setSourceX(particles[i].getSourceX() + particles[i].getVx());
                        particles[i].setSourceY(particles[i].getSourceY() + particles[i].getVy());
                    }
                } else {
                    canvas.restore();
                    invalidate();
                    break;
                }
            }
        }
        else
        {
            for (int i = 0; i < particles.length; i++) {
                canvas.save();
                if (particles[i] != null) {
                    textPaint.setColor(Color.parseColor(particles[i].getParticleColor()));
                    canvas.drawCircle((int) particles[i].getSourceX(), (int) particles[i].getSourceY(), particles[i].getRadius(), textPaint);
                    particles[i].setVx((particles[i].getTargetX() - particles[i].getSourceX()) * releasing);
                    particles[i].setVy((particles[i].getTargetY() - particles[i].getSourceY()) * releasing);
                } else {
                    //Restore saved particles layout. Do not continue to update when paused.
                    canvas.restore();
                    if (!viewUpdated)
                    {
                        invalidate();
                        viewUpdated = true;
                    }
                    break;
                }
            }
        }
    }

    private void setParticles(){
        int[][] colorArray = bitmapTransition();
        int red, green, blue;
        particles = new Particle[(colorArray.length / rowStep) * colorArray[0].length / columnStep];
        int index = 0;
        for (int i = 0; i < colorArray.length; i += rowStep) {
            for (int j = 0; j < colorArray[0].length; j += columnStep) {
                red = Color.red(colorArray[i][j]);
                green = Color.green(colorArray[i][j]);
                blue = Color.blue(colorArray[i][j]);
                //This RGB group is the value of "#3399ff" which defined in initTextPaint()
                if (red == 51 && green == 153 && blue == 255) {
                    particles[index] = new Particle(particleRadius, getRandomColor());
                    movingStrategy.setMovingPath(particles[index], getWidth(), getHeight(), new double[]{j, i});
                    particles[index].setVx((particles[index].getTargetX() - particles[index].getSourceX()) * releasing);
                    particles[index].setVy((particles[index].getTargetY() - particles[index].getSourceY()) * releasing);
                    index++;
                }
            }
        }
    }

    public void setAnimationFrozen() {
        isAnimationFrozen = true;
    }

    public void setAnimationResume() {
        isAnimationFrozen = false;
        viewUpdated = false;
        invalidate();
    }

    private void pauseAnimation() {
        isAnimationPause = true;
        for (Particle item : particles) {
            if (item != null) {
                item.updatePathProcess();
                if (item.getPathProcess() == item.getPath().length - 1){
                    isAnimationStop = true;
                }
                else {
                    isAnimationStop = false;
                }
            } else {
                break;
            }
        }
        if (delay >= 0) {
            if (!isAnimationStop)
            {
                //Setting Particles is a CPU and time consuming process. Do not delay this operation!
                delay = 0;
            }
            else
            {
                //This delay displays the text. Reset delay to value set by user in configs.
                delay = delayHolder;
            }
            rx.Observable.timer(delay, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            isAnimationPause = false;
                            if (!isAnimationStop)
                            {
                                //Call a refresh to display next particle text
                                if (targetTextArray != null)
                                {
                                    setParticles = false;
                                }
                            }
                        }
                    });
        }
    }

    private boolean checkJudgeDistance() {
        boolean hasParticleNotFinish = false;
        for (Particle item : particles) {
            if (item != null) {
                if (Math.abs(item.getVx()) < miniJudgeDistance && Math.abs(item.getVy()) < miniJudgeDistance) {
                    item.setSourceX(item.getTargetX());
                    item.setSourceY(item.getTargetY());
                } else {
                    hasParticleNotFinish = true;
                }
            } else {
                break;
            }
        }
        return hasParticleNotFinish;
    }

    private String getRandomColor() {
        if (particleColorArray == null) {
            String red, green, blue;
            Random random = new Random();

            red = Integer.toHexString(random.nextInt(256)).toUpperCase();
            green = Integer.toHexString(random.nextInt(256)).toUpperCase();
            blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

            red = red.length() == 1 ? "0" + red : red;
            green = green.length() == 1 ? "0" + green : green;
            blue = blue.length() == 1 ? "0" + blue : blue;
            return "#" + red + green + blue;
        } else {
            return particleColorArray[(int) (Math.random() * particleColorArray.length)];
        }
    }
}
