package com.example.kryvyi.android_project

import android.app.Application
import com.example.kryvyi.android_project.data.AppContainer

class BookTrackerApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container =
            AppContainer(
                applicationContext
            )
    }
}
