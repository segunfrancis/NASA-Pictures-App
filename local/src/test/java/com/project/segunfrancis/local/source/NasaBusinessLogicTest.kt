package com.project.segunfrancis.local.source

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.FileNotFoundException

/**
 * Created by SegunFrancis
 */

@RunWith(AndroidJUnit4::class)
class NasaBusinessLogicTest {

    lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testGetData() {
        val logic = NasaBusinessLogic()
        val inputStream = context.assets.open("data.json")
        val data = logic.getData(inputStream)
        assertNotNull(data)
    }

    @Test(expected = FileNotFoundException::class)
    fun testGetData_wrongFileName() {
        val logic = NasaBusinessLogic()
        val inputStream = context.assets.open("data")
        val data = logic.getData(inputStream)
        assertNotNull(data)
    }

    @Test(expected = FileNotFoundException::class)
    fun testGetData_emptyFileName() {
        val logic = NasaBusinessLogic()
        val inputStream = context.assets.open("")
        val data = logic.getData(inputStream)
        assertNotNull(data)
    }
}