package com.example.testtaskforalef

import android.app.Application
import com.example.testtaskforalef.di.AppComponent
import com.example.testtaskforalef.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().app(this).build()
    }
}