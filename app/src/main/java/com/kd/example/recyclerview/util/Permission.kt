package com.kd.example.recyclerview.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import timber.log.Timber

class Permission {
    companion object{
        private var permissionDenied = false
        const val REQUEST_CODE_DEFAULT = 111
        const val PERMISSION_GRANT  = PackageManager.PERMISSION_GRANTED
        const val PERMISSION_DENIED = PackageManager.PERMISSION_DENIED


        private const val REQUEST_PERMISSION_INTERNET               = Manifest.permission.INTERNET
        private const val REQUEST_PERMISSION_CAMERA                 = Manifest.permission.CAMERA
        private const val REQUEST_PERMISSION_READ_EXTERNAL_STORAGE  = Manifest.permission.READ_EXTERNAL_STORAGE
        private fun permissionList() = arrayOf(
            REQUEST_PERMISSION_INTERNET
            ,REQUEST_PERMISSION_CAMERA
            ,REQUEST_PERMISSION_READ_EXTERNAL_STORAGE)
        fun request(act: Activity){

            for(permission in permissionList()){
                Timber.e("List : $permission")
                if(ContextCompat.checkSelfPermission(act, permission) != PERMISSION_GRANT){
                    permissionDenied = true
                    Timber.e("permissionDenied")

                }else{
                    Timber.e("permission Grant")
                }
            }
            act.requestPermissions(permissionList(), REQUEST_CODE_DEFAULT)
        }
    }

}