package com.project.segunfrancis.nasapicturesapp.di

import android.content.Context
import coil.ImageLoader
import coil.util.CoilUtils
import com.project.segunfrancis.domain.di.IOCoroutineDispatcher
import com.project.segunfrancis.domain.di.MainCoroutineDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(SingletonComponent::class)
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
    @IOCoroutineDispatcher
    fun provideIOCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @MainCoroutineDispatcher
    fun provideMainCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main.immediate
    }
}
