package com.project.segunfrancis.nasapicturesapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.domain.usecase.GetDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.InputStream

/**
 * Created by SegunFrancis
 */

class NasaViewModel @ViewModelInject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val inputStream: InputStream,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    fun getData() {
        viewModelScope.launch(dispatcher) {
            getDataUseCase.execute(inputStream)
                .catch {
                    Timber.tag("NasaViewModel").d(it.localizedMessage)
                }
                .collect {
                    Timber.tag("NasaViewModel").d(it[0].explanation)
                }
        }
    }
}