package com.github.yasic.particletextview;

import android.content.Intent;
import android.content.res.ObbInfo;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;

import com.yasic.library.particletextview.MovingStrategy.BidiHorizontalStrategy;
import com.yasic.library.particletextview.MovingStrategy.BidiVerticalStrategy;
import com.yasic.library.particletextview.MovingStrategy.CornerStrategy;
import com.yasic.library.particletextview.MovingStrategy.RandomMovingStrategy;
import com.yasic.library.particletextview.MovingStrategy.VerticalStrategy;
import com.yasic.library.particletextview.Object.Particle;
import com.yasic.library.particletextview.Object.ParticleTextViewConfig;
import com.yasic.library.particletextview.View.ParticleTextSurfaceView;
import com.yasic.library.particletextview.View.ParticleTextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        Button btnViewDemo = (Button)findViewById(R.id.btn_view_demo);
        assert btnViewDemo != null;
        btnViewDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewDemo.class);
                startActivity(intent);
            }
        });

        Button btnSurfaceViewDemo = (Button)findViewById(R.id.btn_surface_view_demo);
        assert btnSurfaceViewDemo != null;
        btnSurfaceViewDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SurfaceViewDemo.class);
                startActivity(intent);
            }
        });

        Button btnViewOnClickDemo = (Button)findViewById(R.id.btn_view_on_click_demo);
        assert btnViewOnClickDemo != null;
        btnViewOnClickDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewOnClickDemo.class);
                startActivity(intent);
            }
        });

        Button btnSurfaceViewOnClickDemo = (Button)findViewById(R.id.btn_surface_view_on_click_demo);
        assert btnSurfaceViewOnClickDemo != null;
        btnSurfaceViewOnClickDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SurfaceViewOnClickDemo.class);
                startActivity(intent);
            }
        });
    }
}
