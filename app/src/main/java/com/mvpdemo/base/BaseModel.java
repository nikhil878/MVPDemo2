package com.mvpdemo.base;

import com.mvpdemo.data.DataManager;

import java.lang.ref.SoftReference;

public abstract class BaseModel<T extends BaseModelListener> {

    private SoftReference<T> listener;

    public BaseModel(T listener) {
        this.listener = new SoftReference<>(listener);
    }

    public void attachListener(T listener) {
        this.listener = new SoftReference<>(listener);
    }

    public void detachListener() {
        this.listener = null;
    }

    public T getListener() {
        return (listener != null) ? listener.get() : null;
    }

    public abstract void init();



    public DataManager getDataManager() {
        return DataManager.getInstance();
    }
}
