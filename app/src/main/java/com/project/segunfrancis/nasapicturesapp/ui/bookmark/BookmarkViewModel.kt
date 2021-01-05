package com.project.segunfrancis.nasapicturesapp.ui.bookmark

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.domain.usecase.GetAllBookmarksUseCase
import com.project.segunfrancis.nasapicturesapp.mapper.NasaItemMapper
import com.project.segunfrancis.nasapicturesapp.model.NasaItem
import com.project.segunfrancis.nasapicturesapp.util.Result
import com.project.segunfrancis.nasapicturesapp.util.asLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookmarkViewModel @ViewModelInject constructor(
    private val getAllBookmarksUseCase: GetAllBookmarksUseCase,
    private val mapper: NasaItemMapper
) :
    ViewModel() {

    private val _allBookmarks = MutableLiveData<Result<List<NasaItem>>>()
    val allBookmarks get() = _allBookmarks.asLiveData()

    init {
        getAllBookmarks()
    }

    private fun getAllBookmarks() {
        viewModelScope.launch {
            getAllBookmarksUseCase()
                .catch { }
                .collect {
                    _allBookmarks.postValue(Result.Success(it.map { item ->
                        mapper.mapDomainToAppLayer(item)
                    }))
                }
        }
    }
}