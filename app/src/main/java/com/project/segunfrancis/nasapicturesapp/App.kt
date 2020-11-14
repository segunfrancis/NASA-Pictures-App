package com.project.segunfrancis.nasapicturesapp

import android.app.Application
import timber.log.Timber

/**
 * Created by SegunFrancis
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}