package com.project.segunfrancis.nasapicturesapp.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.domain.usecase.*
import com.project.segunfrancis.nasapicturesapp.mapper.NasaItemMapper
import com.project.segunfrancis.nasapicturesapp.model.NasaItem
import com.project.segunfrancis.nasapicturesapp.util.Event
import com.project.segunfrancis.nasapicturesapp.util.Result
import com.project.segunfrancis.nasapicturesapp.util.asLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PictureDetailsViewModel @ViewModelInject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val getAllBookmarksUseCase: GetAllBookmarksUseCase,
    private val mapper: NasaItemMapper,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _pictureList = MutableLiveData<Result<List<NasaItem>>>()
    val pictureList get() = _pictureList.asLiveData()

    private val _adapterPosition = MutableLiveData<Event<Int>>()
    val adapterPosition get() =  _adapterPosition.asLiveData()

    fun getPictureList() {
        viewModelScope.launch(dispatcher) {
            getDataUseCase()
                .catch { _pictureList.postValue(Result.Error(it)) }
                .collect { items ->
                    _pictureList.postValue(Result.Success(items.reversed().map {
                        mapper.mapDomainToAppLayer(it)
                    }))
                }
        }
    }

    fun getAllBookmarks() {
        viewModelScope.launch {
            getAllBookmarksUseCase()
                .catch { _pictureList.postValue(Result.Error(it)) }
                .collect {
                    _pictureList.postValue(Result.Success(it.map { item ->
                        mapper.mapDomainToAppLayer(item)
                    }))
                }
        }
    }

    fun setAdapterPosition(position: Int) {
        _adapterPosition.value = Event(position)
    }
}