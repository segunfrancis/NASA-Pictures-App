package com.project.segunfrancis.nasapicturesapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.project.segunfrancis.domain.usecase.GetDataUseCase
import com.project.segunfrancis.nasapicturesapp.mapper.NasaItemMapper
import com.project.segunfrancis.nasapicturesapp.model.NasaItem
import com.project.segunfrancis.nasapicturesapp.util.Event
import com.project.segunfrancis.nasapicturesapp.util.Result
import com.project.segunfrancis.nasapicturesapp.util.asLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.InputStream

/**
 * Created by SegunFrancis
 */

class NasaViewModel @ViewModelInject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val inputStream: InputStream,
    private val mapper: NasaItemMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    init {
        getPictureList()
    }

    private val _pictureList = MutableLiveData<Result<List<NasaItem>>>()
    val pictureList = _pictureList.asLiveData()

    private val _adapterPosition = MutableLiveData<Event<Int>>()
    val adapterPosition = _adapterPosition.asLiveData()

    fun getPictureList() {
        viewModelScope.launch(dispatcher) {
            getDataUseCase.execute(inputStream)
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

    fun setAdapterPosition(position: Int) {
        _adapterPosition.value = Event(position)
    }
}