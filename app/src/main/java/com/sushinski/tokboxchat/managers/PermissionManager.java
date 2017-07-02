package com.sushinski.tokboxchat.managers;


import android.Manifest;
import android.app.Activity;
import android.support.annotation.NonNull;

import com.sushinski.tokboxchat.R;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * App permission manager clas
 */
public class PermissionManager {
    private static final int RC_VIDEO_APP_PERM = 124;
    private final Activity mActivity;
    private boolean mIsPermissionsGranted = false;

    /**
     * Initialises app permission management
     * @param activity Base activity for app permissions init
     */
    public PermissionManager(@NonNull Activity activity){
        mActivity = activity;
        requestPermissions();
    }

    /**
     * Current premissions state
     * @return True if permissions granted
     */
    public boolean isPermissionsGranted(){
        return mIsPermissionsGranted;
    }

    @AfterPermissionGranted(RC_VIDEO_APP_PERM)
    private void requestPermissions() {
        String[] perms = {  Manifest.permission.INTERNET,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO };
        if (EasyPermissions.hasPermissions(mActivity, perms)) {
            mIsPermissionsGranted = true;
        } else {
            mIsPermissionsGranted = false;
                EasyPermissions.requestPermissions(mActivity,
                        mActivity.getResources().getString(R.string.permission_explanation),
                        RC_VIDEO_APP_PERM, perms);
        }
    }


    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults){
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
