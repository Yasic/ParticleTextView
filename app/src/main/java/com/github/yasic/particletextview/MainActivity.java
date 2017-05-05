package com.github.yasic.particletextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.yasic.particletextview.Object.ParticleTextViewConfig;
import com.github.yasic.particletextview.View.ParticleTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParticleTextView particleTextView1 = (ParticleTextView) findViewById(R.id.particleTextView1);
        ParticleTextViewConfig config1 = new ParticleTextViewConfig.Builder()
                .setTargetText("YASIC")
                .setReleasing(0.1)
                .setParticleRadius(1.5f)
                .setMiniDistance(1)
                .instance();
        particleTextView1.setConfig(config1);
        particleTextView1.startAnimation();

        ParticleTextView particleTextView2 = (ParticleTextView) findViewById(R.id.particleTextView2);
        ParticleTextViewConfig config2 = new ParticleTextViewConfig.Builder()
                .setTargetText("ParticleTextView")
                .setReleasing(0.1)
                .setParticleRadius(2)
                .setTextSize(80)
                .setMiniDistance(0.8)
                .instance();
        particleTextView2.setConfig(config2);
        particleTextView2.startAnimation();

        ParticleTextView particleTextView3 = (ParticleTextView) findViewById(R.id.particleTextView3);
        ParticleTextViewConfig config3 = new ParticleTextViewConfig.Builder()
                .setTargetText("十万嬉皮")
                .setReleasing(0.3)
                .setParticleRadius(2)
                .setTextSize(60)
                .instance();
        particleTextView3.setConfig(config3);
        particleTextView3.startAnimation();

        ParticleTextView particleTextView4 = (ParticleTextView) findViewById(R.id.particleTextView4);
        ParticleTextViewConfig config4 = new ParticleTextViewConfig.Builder()
                .setTargetText("Android")
                .setReleasing(0.1)
                .setParticleRadius(1)
                .setTextSize(120)
                .setMiniDistance(1)
                .setParticleRadius(4)
                .setColumnStep(6)
                .setRowStep(6)
                .instance();
        particleTextView4.setConfig(config4);
        particleTextView4.startAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
