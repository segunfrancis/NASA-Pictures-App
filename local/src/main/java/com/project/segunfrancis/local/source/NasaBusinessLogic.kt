package com.project.segunfrancis.local.source

import com.google.gson.Gson
import com.project.segunfrancis.local.model.NasaItemLocal
import org.json.JSONArray
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class NasaBusinessLogic @Inject constructor() {

    fun getData(inputStream: InputStream): List<NasaItemLocal> {
        val nasaInfo = mutableListOf<NasaItemLocal>()
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charset.forName("UTF-8"))
        val items = JSONArray(json)
        val gson = Gson()
        for (i in 0 until items.length()) {
            val obj = items.getJSONObject(i)
            val info = gson.fromJson(obj.toString(), NasaItemLocal::class.java)
            nasaInfo.add(info)
        }
        return nasaInfo
    }
}