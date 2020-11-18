package com.project.segunfrancis.local.mapper

import com.project.segunfrancis.data.model.NasaItemData
import com.project.segunfrancis.local.model.NasaItemLocal
import org.hamcrest.core.Is.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by SegunFrancis
 */

class NasaItemLocalMapperTest {

    private lateinit var local: NasaItemLocal
    private lateinit var nasaItemLocalMapper: NasaItemLocalMapper

    @Before
    fun setup() {
        local =  NasaItemLocal(
            copyright = "ESA/HubbleNASA",
            date = "2019-12-01",
            explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
            hdurl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
            media_type = "image",
            service_version = "v1",
            title = "Starburst Galaxy M94 from Hubble",
            url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
        )
        nasaItemLocalMapper = NasaItemLocalMapper()
    }

    @Test
    fun testMapLocalToData_nonNullObject() {

        val data = nasaItemLocalMapper.mapLocalToData(local)

        assertNotNull(data)
    }

    @Test
    fun testMapLocalToData_returnTypeDifferentFromInput() {

        val data = nasaItemLocalMapper.mapLocalToData(local)

        assertNotSame(data, `is`(local))
    }

    @Test
    fun testMapLocalToData_returnTypeSameAsExpected() {

        val data = nasaItemLocalMapper.mapLocalToData(local)

        assert(data is NasaItemData)
    }
}