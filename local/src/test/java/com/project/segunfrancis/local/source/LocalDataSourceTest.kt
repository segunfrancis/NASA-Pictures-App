package com.project.segunfrancis.local.source

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.project.segunfrancis.local.mapper.NasaItemLocalMapper
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.InputStream

/**
 * Created by SegunFrancis
 */

@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest {

    lateinit var dataSourceImpl: LocalDataSourceImpl
    lateinit var inputStream: InputStream

    @Before
    fun setup() {
        val mapper = NasaItemLocalMapper()
        val businessLogic = NasaBusinessLogic()
        dataSourceImpl = LocalDataSourceImpl(mapper, businessLogic)
        inputStream = ApplicationProvider.getApplicationContext<Context>().assets.open("data.json")
    }

    @Test
    fun testGetData() {

        val data = dataSourceImpl.getData(inputStream)

        assertNotNull(data)
    }
}