package com.mvpdemo.splashsample;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mvpdemo.R;
import com.mvpdemo.base.BaseActivity;
import com.mvpdemo.homesample.HomePageActivity;

public class SplashActivity extends BaseActivity implements SplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashPresenter splashPresenter = new SplashPresenter(this);
        splashPresenter.showSplash();
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    public void showSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomePageActivity.class));
                finish();
            }
        }, 3000);
    }
}
