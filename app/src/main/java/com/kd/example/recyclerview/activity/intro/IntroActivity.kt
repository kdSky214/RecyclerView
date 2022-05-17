package com.kd.example.recyclerview.activity.intro

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.kd.example.recyclerview.R
import com.kd.example.recyclerview.activity.MainActivity
import com.kd.example.recyclerview.databinding.ActivityMainBinding
import com.kd.example.recyclerview.activity.base.BaseActivity
import com.kd.example.recyclerview.databinding.ActivityIntroBinding
import com.kd.example.recyclerview.util.Permission
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroActivity : BaseActivity<ActivityIntroBinding>() {

    private val viewModel:IntroViewModel by viewModel()
    override val layoutResourceID: Int
        get() = R.layout.activity_main

    override fun initUI() {
        Permission.request(this)
    }


    override fun initObservers() {
        //TODO("Not yet implemented")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == Permission.REQUEST_CODE_DEFAULT){
            var allGranted = true
            grantResults.forEach {
                if(it == Permission.PERMISSION_DENIED){
                    allGranted = false
                    Toast.makeText(this, "권한을 허용해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }

            if(allGranted){
                nextActivity()
            }else{
                showDialogPermissionSettingPage()
            }
        }
    }


    private fun nextActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    private fun showDialogPermissionSettingPage(){
        val builder = AlertDialog.Builder(this).apply {
            setTitle("알림")
            setMessage("권한을 허용해 주세요")
            setPositiveButton("예") { dialog, id ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName")).apply {
                    addCategory(Intent.CATEGORY_DEFAULT)
                    setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
                dialog.dismiss()
            }
            setNegativeButton("아니요"){dialog, id->
                dialog.dismiss()
                finish()
            }
        }
        builder.create().show()
    }
}