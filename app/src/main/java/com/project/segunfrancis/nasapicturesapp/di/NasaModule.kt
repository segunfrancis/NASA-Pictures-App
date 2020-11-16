package com.project.segunfrancis.nasapicturesapp.di

import android.content.Context
import android.content.SharedPreferences
import coil.ImageLoader
import coil.util.CoilUtils
import com.project.segunfrancis.nasapicturesapp.util.AppConstants.SHARED_PREF_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ApplicationComponent::class)
class NasaModule {

    companion object {
        private const val CALL_TIMEOUT: Long = 20L
        private const val READ_TIMEOUT: Long = 20L
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .cache(CoilUtils.createDefaultCache(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    fun provideInputStream(@ApplicationContext context: Context): InputStream {
        return context.assets.open("data.json")
    }

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }
}
