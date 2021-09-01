package com.project.segunfrancis.domain.usecase

import com.project.segunfrancis.domain.di.IOCoroutineDispatcher
import com.project.segunfrancis.domain.model.NasaItemDomain
import com.project.segunfrancis.domain.repository.LocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoveBookmarkUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    @IOCoroutineDispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(nasaItemDomain: NasaItemDomain): Flow<Unit> {
        return localRepository.removeBookmark(nasaItemDomain).flowOn(dispatcher)
    }
}