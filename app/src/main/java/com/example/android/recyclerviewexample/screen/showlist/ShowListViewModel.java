package com.example.android.recyclerviewexample.screen.showlist;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

public class ShowListViewModel extends BaseObservable implements ShowListContract.ViewModel{
    
    private static final String TAG = ShowListViewModel.class.getSimpleName();
    private ShowListContract.Presenter mPresenter;
    
    public ShowListViewModel() {
        
    }
    
    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ShowListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRequestPermissionsReuslt(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        mPresenter.onRequestPermissionsReuslt(requestCode,permissions,grantResults);
    }
}
