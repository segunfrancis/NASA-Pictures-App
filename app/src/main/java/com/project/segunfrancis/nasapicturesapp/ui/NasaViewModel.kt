package com.project.segunfrancis.nasapicturesapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.project.segunfrancis.domain.usecase.GetDataUseCase
import com.project.segunfrancis.nasapicturesapp.mapper.NasaItemMapper
import com.project.segunfrancis.nasapicturesapp.model.NasaItem
import com.project.segunfrancis.nasapicturesapp.util.Event
import com.project.segunfrancis.nasapicturesapp.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import java.io.InputStream

/**
 * Created by SegunFrancis
 */

class NasaViewModel @ViewModelInject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val inputStream: InputStream,
    private val mapper: NasaItemMapper,
    dispatcher: CoroutineDispatcher
) : ViewModel() {

    val pictureList: LiveData<Result<List<NasaItem>>> = liveData(dispatcher) {
        getDataUseCase.execute(inputStream)
            .catch {
                emit(Result.Error(it))
            }
            .collect { items ->
                emit(Result.Success(items.reversed().map {
                    mapper.mapDomainToAppLayer(it)
                }))
            }
    }

    val adapterPosition = MutableLiveData<Event<Int>>()
}