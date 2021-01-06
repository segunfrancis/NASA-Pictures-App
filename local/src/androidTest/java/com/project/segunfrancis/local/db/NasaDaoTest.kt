package com.project.segunfrancis.local.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.project.segunfrancis.local.model.NasaItemLocal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NasaDaoTest {

    private lateinit var database: NasaDatabase
    private val context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var controlItem: NasaItemLocal

    @Before
    fun init() {
        database =
            Room.inMemoryDatabaseBuilder(context, NasaDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        controlItem = NasaItemLocal(
            copyright = "ESA/HubbleNASA",
            date = "2019-12-01",
            explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
            hdurl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
            media_type = "image",
            service_version = "v1",
            title = "Starburst Galaxy M94 from Hubble",
            url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
        )
    }

    @Test
    fun test_GetsObjectFromDatabase() = runBlocking {
        database.dao().addBookmark(controlItem)
        val item = database.dao().getAllBookmarks()
        assertNotNull(item)
    }

    @Test
    fun test_GetsFirstObjectFromDatabase() = runBlocking {
        database.dao().addBookmark(controlItem)
        val item = database.dao().getAllBookmarks().first()
        assertEquals(controlItem, item[0])
    }

    @Test
    fun test_DeletesObjectFromDatabase() = runBlocking {
        database.dao().addBookmark(controlItem)
        database.dao().removeBookmark(controlItem)
        val item = database.dao().getAllBookmarks().first()
        assert(item.isEmpty())
    }

    @After
    fun tearDown() {
        database.close()
    }
}