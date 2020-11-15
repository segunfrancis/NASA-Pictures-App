package com.project.segunfrancis.nasapicturesapp.di

import android.content.Context
import coil.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import java.io.InputStream
import javax.inject.Singleton

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ApplicationComponent::class)
class NasaModule {

    @Provides
    @Singleton
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(OkHttpClient.Builder().build())
            .build()
    }

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideInputStream(@ApplicationContext context: Context): InputStream {
        return context.assets.open("data.json")
    }
}
