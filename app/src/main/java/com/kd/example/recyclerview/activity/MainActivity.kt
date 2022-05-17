package com.kd.example.recyclerview.activity

import com.kd.example.recyclerview.R
import com.kd.example.recyclerview.databinding.ActivityMainBinding
import com.kd.example.recyclerview.activity.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel:MainViewModel by viewModel()
    override val layoutResourceID: Int
        get() = R.layout.activity_main

    override fun initUI() {
        //TODO("Not yet implemented")
    }

    override fun initObservers() {
        //TODO("Not yet implemented")
    }

}