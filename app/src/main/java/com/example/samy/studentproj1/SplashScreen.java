package com.example.samy.studentproj1;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {
    private static final long SPLASH_TIME_OUT = 5000;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startAnimation();
    }

    private void startAnimation(){
        Animation anim=AnimationUtils.loadAnimation(this,R.anim.alpha);
        anim.reset();
        LinearLayout l =findViewById(R.id.splash_screen_linear_layout);
        l.clearAnimation();
        l.startAnimation(anim);

        anim =AnimationUtils.loadAnimation(this,R.anim.translate);
        anim.reset();
        ImageView iv=findViewById(R.id.splash_screen_image_view);
        iv.clearAnimation();
        iv.startAnimation(anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,
                        MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}
