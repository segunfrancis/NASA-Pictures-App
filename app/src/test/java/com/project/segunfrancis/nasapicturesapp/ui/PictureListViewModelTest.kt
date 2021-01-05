package com.project.segunfrancis.nasapicturesapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.project.segunfrancis.data.repository.LocalRepositoryImpl
import com.project.segunfrancis.domain.repository.LocalRepository
import com.project.segunfrancis.domain.usecase.GetAllBookmarksUseCase
import com.project.segunfrancis.domain.usecase.GetDataUseCase
import com.project.segunfrancis.nasapicturesapp.getOrAwaitValue
import com.project.segunfrancis.nasapicturesapp.mapper.NasaItemMapper
import com.project.segunfrancis.nasapicturesapp.ui.details.PictureDetailsViewModel
import com.project.segunfrancis.nasapicturesapp.util.Result
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.testing.*
import kotlinx.coroutines.CoroutineDispatcher
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.annotation.Config
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

@UninstallModules(RepositoryModule::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class PictureListViewModelTest {

    @Module
    @InstallIn(ActivityRetainedComponent::class)
    class TestRepositoryModule {

        @Provides
        fun localRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository {
            return mock(LocalRepository::class.java)
        }
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @BindValue @JvmField
    val localRepository: LocalRepository = mock(LocalRepository::class.java)

    @Inject lateinit var getDataUseCase: GetDataUseCase
    @Inject lateinit var getAllBookmarksUseCase: GetAllBookmarksUseCase
    @Inject lateinit var inputStream: InputStream
    @Inject lateinit var mapper: NasaItemMapper
    @Inject lateinit var dispatcher: CoroutineDispatcher

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testGetPictureList() {

        val viewModel = PictureDetailsViewModel(getDataUseCase, getAllBookmarksUseCase, inputStream, mapper, dispatcher)
        viewModel.getPictureList()
        val value = viewModel.pictureList.getOrAwaitValue()
        assertThat(value, not(nullValue()))
    }

    @Test
    fun testGetPictureList_isNotEmpty() {

        val viewModel = PictureDetailsViewModel(getDataUseCase, getAllBookmarksUseCase, inputStream, mapper, dispatcher)
        viewModel.getPictureList()
        val value = viewModel.pictureList.getOrAwaitValue()
        if (value is Result.Success)
            assert(value.data.isNotEmpty())
    }

    @Test
    fun testSetAdapterPosition() {

        val viewModel = PictureDetailsViewModel(getDataUseCase, getAllBookmarksUseCase, inputStream, mapper, dispatcher)
        viewModel.setAdapterPosition(2)
        val expected = 2
        val actual = viewModel.adapterPosition.getOrAwaitValue().peekContent()
        assertThat(actual, `is`(expected))
    }

    @Test
    fun testSetAdapterPosition_notNull() {

        val viewModel = PictureDetailsViewModel(getDataUseCase, getAllBookmarksUseCase, inputStream, mapper, dispatcher)
        viewModel.setAdapterPosition(1)
        assertThat(viewModel.adapterPosition.getOrAwaitValue(), not(nullValue()))
    }
}