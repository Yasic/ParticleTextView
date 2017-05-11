package com.yasic.library.particletextview.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Script;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.yasic.library.particletextview.MovingStrategy.MovingStrategy;
import com.yasic.library.particletextview.Object.Particle;
import com.yasic.library.particletextview.Object.ParticleTextViewConfig;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ParticleTextSurfaceView extends SurfaceView {
    private SurfaceHolder surfaceHolder = null;
    private Paint textPaint;
    private Bitmap bitmap;
    private Particle[] particles = null;
    private float canvasWidth = 0;
    private float canvasHeight = 0;
    private boolean isAnimationOn = false;
    private boolean isAnimationPause = false;
    private boolean isAnimationStop = false;
    private DrawParticle drawParticle = null;
    private boolean threadOn = true;

    private int columnStep;
    private int rowStep;
    private double releasing;
    private double miniJudgeDistance;
    private String targetText = null;
    private int textSize;
    private float particleRadius;
    private String[] particleColorArray = null;
    private MovingStrategy movingStrategy = null;
    private long delay = 1000;


    public ParticleTextSurfaceView(Context context) {
        super(context);
        ParticleTextViewConfig tempConfig = new ParticleTextViewConfig.Builder().instance();
        setConfig(tempConfig);
    }

    public ParticleTextSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ParticleTextViewConfig tempConfig = new ParticleTextViewConfig.Builder().instance();
        setConfig(tempConfig);
    }

    public void startAnimation() {
        this.isAnimationOn = true;
    }

    public void stopAnimation() {
        this.isAnimationOn = false;
    }

    public boolean isAnimationPause() {
        return this.isAnimationPause;
    }

    public boolean isAnimationStop() {
        return this.isAnimationStop;
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
            this.textSize = config.getTextSize();
            this.particleRadius = config.getParticleRadius();
            this.particleColorArray = config.getParticleColorArray();
            this.movingStrategy = config.getMovingStrategy();
            this.delay = config.getDelay();
        } else {
            Log.e("CONFIGERROR", "ParticleTextView Config is Null");
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        canvasWidth = getWidth();
        canvasHeight = getHeight();
        surfaceHolder = this.getHolder();
        if (drawParticle == null){
            drawParticle = new DrawParticle();
        }
        surfaceHolder.addCallback(drawParticle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (drawParticle != null){
            threadOn = false;
        }
    }

    private int[][] bitmapTransition(int centerX, int centerY) {
        int[][] colorArray;
        textPaint = initTextPaint();
        bitmap = Bitmap.createBitmap(centerX * 2, centerY * 2, Bitmap.Config.ARGB_8888);
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

    private class DrawParticle implements SurfaceHolder.Callback {
        private Thread drawThread = null;

        @Override
        public void surfaceCreated(final SurfaceHolder surfaceHolder) {
            setParticles();
        }

        @Override
        public void surfaceChanged(final SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            drawThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (threadOn) {
                        synchronized(surfaceHolder){
                            Canvas canvas = surfaceHolder.lockCanvas();
                            if (canvas == null) {
                                continue;
                            }
                            Paint mPaint = new Paint();
                            mPaint.setAntiAlias(true);
                            mPaint.setColor(Color.WHITE);
                            canvas.drawRect(0, 0, canvasWidth, canvasHeight, mPaint);

                            if (!isAnimationOn) {
                                setParticles();
                                surfaceHolder.unlockCanvasAndPost(canvas);
                                continue;
                            }

                            if (!checkJudgeDistance()) {
                                pauseAnimation();
                            }

                            for (int i = 0; i < particles.length; i++) {
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
                                    break;
                                }
                            }
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            });
            drawThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (drawThread == null) {
                threadOn = false;
            }
        }

        private void setParticles(){
            int centerX = (int) (canvasWidth / 2);
            int centerY = (int) (canvasHeight / 2);
            int[][] colorArray = bitmapTransition(centerX, centerY);
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
            rx.Observable.timer(delay, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            isAnimationPause = false;
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
