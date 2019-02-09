package com.example.samy.studentproj1;

import android.app.Application;
import android.content.Intent;

import com.example.samy.studentproj1.activities.LoginActivity;

public class Home extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent=new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
