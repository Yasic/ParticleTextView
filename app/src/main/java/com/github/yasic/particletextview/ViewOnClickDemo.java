package com.github.yasic.particletextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yasic.library.particletextview.Object.ParticleTextViewConfig;
import com.yasic.library.particletextview.View.ParticleTextView;

public class ViewOnClickDemo extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_on_click_demo);
        final ParticleTextView particleTextView = (ParticleTextView) findViewById(R.id.particleTextView);
        ParticleTextViewConfig config1 = new ParticleTextViewConfig.Builder()
                .setTargetText(new String[]{"Loading", "Sample", "Message"})
                .setReleasing(0.05)
                .setParticleRadius(5)
                .setMiniDistance(0.5)
                .setTextSize(150)
                .setRowStep(8)
                .setColumnStep(8)
                .instance();
        particleTextView.setConfig(config1);

        Button btnStartAnimation = (Button)findViewById(R.id.btn_start_animation);
        assert btnStartAnimation != null;
        btnStartAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleTextView.startAnimation();
            }
        });

        Button btnStopAnimation = (Button)findViewById(R.id.btn_stop_animation);
        assert btnStopAnimation != null;
        btnStopAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleTextView.stopAnimation();
            }
        });

        Button btnPauseAnimation = (Button)findViewById(R.id.btn_pause_animation);
        assert btnPauseAnimation != null;
        btnPauseAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleTextView.setAnimationFrozen();
            }
        });

        Button btnResumeAnimation = (Button)findViewById(R.id.btn_resume_animation);
        assert btnResumeAnimation != null;
        btnResumeAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleTextView.setAnimationResume();
            }
        });
    }
}
