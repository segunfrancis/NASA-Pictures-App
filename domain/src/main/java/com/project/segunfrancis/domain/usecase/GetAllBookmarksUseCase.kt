package com.project.segunfrancis.domain.usecase

import com.project.segunfrancis.domain.model.NasaItemDomain
import com.project.segunfrancis.domain.repository.LocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllBookmarksUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<NasaItemDomain>> {
        return localRepository.getAllBookmarks().flowOn(dispatcher)
    }
}