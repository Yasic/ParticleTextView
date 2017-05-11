package com.github.yasic.particletextview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yasic.library.particletextview.MovingStrategy.BidiHorizontalStrategy;
import com.yasic.library.particletextview.MovingStrategy.BidiVerticalStrategy;
import com.yasic.library.particletextview.MovingStrategy.CornerStrategy;
import com.yasic.library.particletextview.MovingStrategy.VerticalStrategy;
import com.yasic.library.particletextview.Object.ParticleTextViewConfig;
import com.yasic.library.particletextview.View.ParticleTextView;

public class ViewDemo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_demo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ParticleTextView particleTextView1 = (ParticleTextView) findViewById(R.id.particleTextView1);
        ParticleTextViewConfig config1 = new ParticleTextViewConfig.Builder()
                .setTargetText("Loading")
                .setReleasing(0.4)
                .setParticleRadius(4)
                .setMiniDistance(1)
                .setTextSize(150)
                .setRowStep(9)
                .setColumnStep(9)
                .instance();
        particleTextView1.setConfig(config1);

        ParticleTextView particleTextView2 = (ParticleTextView) findViewById(R.id.particleTextView2);
        VerticalStrategy verticalStrategy = new VerticalStrategy();
        ParticleTextViewConfig config2 = new ParticleTextViewConfig.Builder()
                .setTargetText("ParticleTextView")
                .setReleasing(0.5)
                .setParticleRadius(3)
                .setTextSize(120)
                .setMiniDistance(0.5)
                .setColumnStep(5)
                .setRowStep(5)
                .setParticleColorArray(new String[]{"#333333", "#222222", "#111111"})
                .setMovingStrategy(verticalStrategy)
                .instance();
        particleTextView2.setConfig(config2);

        ParticleTextView particleTextView3 = (ParticleTextView) findViewById(R.id.particleTextView3);
        CornerStrategy cornerStrategy = new CornerStrategy();
        ParticleTextViewConfig config3 = new ParticleTextViewConfig.Builder()
                .setTargetText("Java")
                .setReleasing(0.3)
                .setParticleRadius(4)
                .setTextSize(150)
                .setColumnStep(6)
                .setRowStep(6)
                .setMovingStrategy(cornerStrategy)
                .setParticleColorArray(new String[]{"#9933ff"})
                .instance();
        particleTextView3.setConfig(config3);

        ParticleTextView particleTextView4 = (ParticleTextView) findViewById(R.id.particleTextView4);
        BidiVerticalStrategy movingStrategy4 = new BidiVerticalStrategy();
        ParticleTextViewConfig config4 = new ParticleTextViewConfig.Builder()
                .setTargetText("Android")
                .setReleasing(0.3)
                .setTextSize(150)
                .setMiniDistance(0.01)
                .setParticleRadius(4)
                .setColumnStep(6)
                .setRowStep(6)
                .setDelay((long) 500)
                .setParticleColorArray(new String[]{"#99ff33"})
                .setMovingStrategy(movingStrategy4)
                .instance();
        particleTextView4.setConfig(config4);

        ParticleTextView particleTextView5 = (ParticleTextView) findViewById(R.id.particleTextView5);
        BidiHorizontalStrategy movingStrategy5 = new BidiHorizontalStrategy();
        ParticleTextViewConfig config5 = new ParticleTextViewConfig.Builder()
                .setTargetText("Canvas")
                .setReleasing(0.3)
                .setTextSize(150)
                .setMiniDistance(0.01)
                .setParticleRadius(4)
                .setColumnStep(8)
                .setRowStep(8)
                .setMovingStrategy(movingStrategy5)
                .instance();
        particleTextView5.setConfig(config5);

        particleTextView1.startAnimation();
        particleTextView2.startAnimation();
        particleTextView3.startAnimation();
        particleTextView4.startAnimation();
        particleTextView5.startAnimation();
    }
}
