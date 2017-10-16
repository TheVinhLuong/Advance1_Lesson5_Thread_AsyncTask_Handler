package com.example.android.recyclerviewexample.screen.showlist;


import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.permission.PermissionHandler;

/**
 * Listens to user actions from the UI ({@link ShowListActivity}), retrieves the data and updates
 * the UI as required.
 */
final class ShowListPresenter implements ShowListContract.Presenter {
    private static final String TAG = ShowListPresenter.class.getSimpleName();
    private final ShowListContract.ViewModel mViewModel;
    private static final int REQUEST_CODE_PERMISSION = 99;
    private String[] mPermissions = { Manifest.permission.READ_EXTERNAL_STORAGE};
    private PermissionHandler mPermissionHandler;

    public ShowListPresenter(ShowListContract.ViewModel viewModel, PermissionHandler permissionHandler) {
        mViewModel = viewModel;
        mPermissionHandler = permissionHandler;
        mPermissionHandler.setPermissions(mPermissions);
        mPermissionHandler.requestPermission(REQUEST_CODE_PERMISSION);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onItemWordClicked(Word word) {
        Log.d(TAG, "onItemClicked: " + word.getWord());
    }

    @Override
    public void onRequestPermissionsReuslt(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_PERMISSION){
            for(int result : grantResults){
                if(result != PackageManager.PERMISSION_GRANTED){
                    mPermissionHandler.requestPermission(REQUEST_CODE_PERMISSION);
                    break;
                }
            }
        }
    }
}
