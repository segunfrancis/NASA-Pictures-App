package com.project.segunfrancis.nasapicturesapp.ui.list

import androidx.lifecycle.*
import com.project.segunfrancis.domain.usecase.*
import com.project.segunfrancis.nasapicturesapp.mapper.NasaItemMapper
import com.project.segunfrancis.nasapicturesapp.model.NasaItem
import com.project.segunfrancis.nasapicturesapp.util.Event
import com.project.segunfrancis.nasapicturesapp.util.Message
import com.project.segunfrancis.nasapicturesapp.util.Result
import com.project.segunfrancis.nasapicturesapp.util.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

@HiltViewModel
class PictureListViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
    private val addBookmarkToSharedPrefUseCase: AddBookmarkToSharedPrefUseCase,
    private val removeBookmarkFromSharedPrefUseCase: RemoveBookmarkFromSharedPrefUseCase,
    private val isBookmarkUseCase: IsBookmarkUseCase,
    private val mapper: NasaItemMapper
) : ViewModel() {

    init {
        getPictureList()
    }

    private val _pictureList = MutableLiveData<Result<List<NasaItem>>>()
    val pictureList get() = _pictureList.asLiveData()

    private val _bookmarkMessage = MutableLiveData<Event<Message>>()
    val bookmarkMessage get() = _bookmarkMessage.asLiveData()

    private fun getPictureList() {
        viewModelScope.launch {
            getDataUseCase()
                .catch {
                    _pictureList.postValue(Result.Error(it))
                }
                .collect { items ->
                    _pictureList.postValue(Result.Success(items.reversed().map {
                        mapper.mapDomainToAppLayer(it)
                    }))
                }
        }
    }

    fun addBookmark(nasaItem: NasaItem) {
        viewModelScope.launch {
            addBookmarkUseCase(mapper.mapAppToDomainLayer(nasaItem))
                .catch { }
                .onCompletion { _bookmarkMessage.postValue(Event(Message("Added to bookmarks", false))) }
                .collect { addBookmarkToSharedPrefUseCase(nasaItem.date).collect() }
        }
    }

    fun removeBookmark(nasaItem: NasaItem) {
        viewModelScope.launch {
            removeBookmarkUseCase(mapper.mapAppToDomainLayer(nasaItem))
                .catch { }
                .onCompletion { _bookmarkMessage.postValue(Event(Message("Removed from bookmarks", true))) }
                .collect { removeBookmarkFromSharedPrefUseCase(nasaItem.date).collect() }
        }
    }

    fun isBookmark(nasaItem: NasaItem): LiveData<Boolean> {
        return liveData {
            isBookmarkUseCase(nasaItem.date).collect { emit(it) }
        }
    }
}