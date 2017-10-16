package com.example.android.recyclerviewexample.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by VinhTL on 12/10/2017.
 */

public class PermissionHandler {
    private static final String TAG = PermissionHandler.class.getSimpleName();
    private Context mContext;
    private String[] mPermissions;

    public PermissionHandler(Context context) {
        mContext = context;
    }

    public String[] getPermissions() {
        return mPermissions;
    }

    public void setPermissions(String[] permissions) {
        mPermissions = permissions;
    }

    private boolean isAllPermissionsGranted() {
        boolean granted = true;
        if (mContext != null) {
            for (String permission : mPermissions) {
                if (ContextCompat.checkSelfPermission(mContext, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    granted = false;
                    break;
                }
            }
        }
        Log.d(TAG, "granted = " + granted);
        return granted;
    }

    private boolean isPermissionGranted(String permission) {
        if (mContext != null) {
            if (ContextCompat.checkSelfPermission(mContext, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void requestPermission(int requestCode) {
        if (!isAllPermissionsGranted()) {
            for(String permission : mPermissions) {
                if(!isPermissionGranted(permission)) {
                    Log.d(TAG, "requested");
                    if (ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity) 
                                    mContext,
                            permission)) {
                        ActivityCompat.requestPermissions((AppCompatActivity) mContext, mPermissions,
                                requestCode);
                    } else {
                        ActivityCompat.requestPermissions((AppCompatActivity) mContext,
                                mPermissions, requestCode);
                    }
                    break;
                }
            }
        }
    }
}
