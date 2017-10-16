package com.example.android.recyclerviewexample.screen;

/**
 * Created by VinhTL on 09/10/2017.
 */

public interface BaseViewModel<Presenter> {
    public void onStart();
    public void onStop();
    public void setPresenter(Presenter p);
}
