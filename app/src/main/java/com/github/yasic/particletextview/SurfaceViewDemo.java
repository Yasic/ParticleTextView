package com.github.yasic.particletextview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yasic.library.particletextview.MovingStrategy.BidiHorizontalStrategy;
import com.yasic.library.particletextview.MovingStrategy.BidiVerticalStrategy;
import com.yasic.library.particletextview.MovingStrategy.CornerStrategy;
import com.yasic.library.particletextview.MovingStrategy.VerticalStrategy;
import com.yasic.library.particletextview.Object.ParticleTextViewConfig;
import com.yasic.library.particletextview.View.ParticleTextSurfaceView;

public class SurfaceViewDemo extends AppCompatActivity {
    private ParticleTextSurfaceView particleTextSurfaceView1;
    private ParticleTextSurfaceView particleTextSurfaceView2;
    private ParticleTextSurfaceView particleTextSurfaceView3;
    private ParticleTextSurfaceView particleTextSurfaceView4;
    private ParticleTextSurfaceView particleTextSurfaceView5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view_demo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        particleTextSurfaceView1 = (ParticleTextSurfaceView)findViewById(R.id.surfaceview1);
        ParticleTextViewConfig config1 = new ParticleTextViewConfig.Builder()
                .setTargetText("Loading")
                .setReleasing(0.2)
                .setParticleRadius(4)
                .setMiniDistance(1)
                .setTextSize(150)
                .setRowStep(9)
                .setColumnStep(9)
                .instance();
        particleTextSurfaceView1.setConfig(config1);

        particleTextSurfaceView2 = (ParticleTextSurfaceView)findViewById(R.id.surfaceview2);
        VerticalStrategy verticalStrategy = new VerticalStrategy();
        ParticleTextViewConfig config2 = new ParticleTextViewConfig.Builder()
                .setTargetText("ParticleTextView")
                .setReleasing(0.1)
                .setParticleRadius(3)
                .setTextSize(120)
                .setMiniDistance(0.5)
                .setColumnStep(5)
                .setRowStep(5)
                .setParticleColorArray(new String[]{"#333333", "#222222", "#111111"})
                .setMovingStrategy(verticalStrategy)
                .instance();
        particleTextSurfaceView2.setConfig(config2);

        particleTextSurfaceView3 = (ParticleTextSurfaceView)findViewById(R.id.surfaceview3);
        CornerStrategy cornerStrategy = new CornerStrategy();
        ParticleTextViewConfig config3 = new ParticleTextViewConfig.Builder()
                .setTargetText("Java")
                .setReleasing(0.1)
                .setParticleRadius(4)
                .setTextSize(150)
                .setColumnStep(6)
                .setRowStep(6)
                .setMovingStrategy(cornerStrategy)
                .setParticleColorArray(new String[]{"#9933ff"})
                .instance();
        particleTextSurfaceView3.setConfig(config3);

        particleTextSurfaceView4 = (ParticleTextSurfaceView)findViewById(R.id.surfaceview4);
        BidiVerticalStrategy movingStrategy4 = new BidiVerticalStrategy();
        ParticleTextViewConfig config4 = new ParticleTextViewConfig.Builder()
                .setTargetText("Android")
                .setReleasing(0.1)
                .setTextSize(150)
                .setMiniDistance(0.01)
                .setParticleRadius(4)
                .setColumnStep(6)
                .setRowStep(6)
                .setDelay((long) 500)
                .setParticleColorArray(new String[]{"#99ff33"})
                .setMovingStrategy(movingStrategy4)
                .instance();
        particleTextSurfaceView4.setConfig(config4);

        particleTextSurfaceView5 = (ParticleTextSurfaceView)findViewById(R.id.surfaceview5);
        BidiHorizontalStrategy movingStrategy5 = new BidiHorizontalStrategy();
        ParticleTextViewConfig config5 = new ParticleTextViewConfig.Builder()
                .setTargetText("Canvas")
                .setReleasing(0.1)
                .setTextSize(150)
                .setMiniDistance(0.01)
                .setParticleRadius(4)
                .setColumnStep(8)
                .setRowStep(8)
                .setMovingStrategy(movingStrategy5)
                .instance();
        particleTextSurfaceView5.setConfig(config5);

        particleTextSurfaceView1.startAnimation();
        particleTextSurfaceView2.startAnimation();
        particleTextSurfaceView3.startAnimation();
        particleTextSurfaceView4.startAnimation();
        particleTextSurfaceView5.startAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        particleTextSurfaceView1.setVisibility(View.GONE);
        particleTextSurfaceView2.setVisibility(View.GONE);
        particleTextSurfaceView3.setVisibility(View.GONE);
        particleTextSurfaceView4.setVisibility(View.GONE);
        particleTextSurfaceView5.setVisibility(View.GONE);
    }
}
