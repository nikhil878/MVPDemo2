package com.mvpdemo.splashsample;

import com.mvpdemo.base.BasePresenter;

public class SplashPresenter extends BasePresenter<SplashView> {
    public SplashPresenter(SplashView view) {
        super(view);
    }

    @Override
    protected void setModel() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    protected void initView() {

    }

    public void showSplash() {
        getView().showSplash();
    }
}
