package com.kd.example.recyclerview

import android.app.Application
import com.kd.example.recyclerview.di.NetworkModule
import com.kd.example.recyclerview.di.RepositoryModule
import com.kd.example.recyclerview.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        createKoin()
    }
    private fun initTimber(){
        Timber.plant(Timber.DebugTree())
        Timber.tag("[RecyclerView TEST]")
    }

    private fun createKoin(){
        startKoin {
            androidLogger()
            androidContext(this@App)
            koin.loadModules(listOf(NetworkModule, RepositoryModule, ViewModelModule))
        }
    }
}