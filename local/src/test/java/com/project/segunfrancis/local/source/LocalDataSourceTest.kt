package com.project.segunfrancis.local.source

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.project.segunfrancis.local.MainCoroutineRule
import com.project.segunfrancis.local.mapper.NasaItemLocalMapper
import com.project.segunfrancis.local.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by SegunFrancis
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest {

    @get:Rule
    val coroutineRule: MainCoroutineRule = MainCoroutineRule()

    private lateinit var dataSourceImpl: LocalDataSourceImpl
    lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        val mapper = NasaItemLocalMapper()
        val businessLogic = NasaBusinessLogic(context)
        //dataSourceImpl = LocalDataSourceImpl(mapper, businessLogic)
    }

    @Test
    fun testGetData() {

        val data = dataSourceImpl.getData()

        assertNotNull(data)
    }

    @Test
    fun testGetData_listNotEmpty() = coroutineRule.runBlockingTest {

        val data = dataSourceImpl.getData()
        data.collect {
            assert(it.isNotEmpty())
        }
    }
}