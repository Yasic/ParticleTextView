package com.github.yasic.particletextview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yasic.library.particletextview.Object.ParticleTextViewConfig;
import com.yasic.library.particletextview.View.ParticleTextSurfaceView;
import com.yasic.library.particletextview.View.ParticleTextView;

public class SurfaceViewOnClickDemo extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view_on_click_demo);

        final ParticleTextSurfaceView particleTextSurfaceView = (ParticleTextSurfaceView) findViewById(R.id.particleSurfaceView);
        ParticleTextViewConfig config = new ParticleTextViewConfig.Builder()
                .setTargetText("Loading")
                .setReleasing(0.4)
                .setParticleRadius(4)
                .setMiniDistance(1)
                .setTextSize(150)
                .setRowStep(9)
                .setColumnStep(9)
                .instance();
        particleTextSurfaceView.setConfig(config);

        Button btnStartAnimation = (Button)findViewById(R.id.btn_start_animation);
        assert btnStartAnimation != null;
        btnStartAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleTextSurfaceView.startAnimation();
            }
        });

        Button btnStopAnimation = (Button)findViewById(R.id.btn_stop_animation);
        assert btnStopAnimation != null;
        btnStopAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleTextSurfaceView.stopAnimation();
            }
        });
    }
}
