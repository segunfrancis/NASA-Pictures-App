package com.project.segunfrancis.domain.usecase

import com.project.segunfrancis.domain.model.NasaItemDomain
import com.project.segunfrancis.domain.repository.LocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class GetDataUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val dispatcher: CoroutineDispatcher
) {
    fun execute(inputStream: InputStream): Flow<List<NasaItemDomain>> {
        return localRepository.getData(inputStream).flowOn(dispatcher)
    }
}