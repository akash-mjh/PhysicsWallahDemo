package com.physicswallahdemo

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize the TimeViewModel
        AppViewModelProvider.getMainViewModel()
    }
}