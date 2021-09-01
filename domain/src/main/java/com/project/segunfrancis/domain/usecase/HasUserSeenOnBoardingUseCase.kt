package com.project.segunfrancis.domain.usecase

import com.project.segunfrancis.domain.di.MainCoroutineDispatcher
import com.project.segunfrancis.domain.repository.SharedPrefRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HasUserSeenOnBoardingUseCase @Inject constructor(
    private val prefRepository: SharedPrefRepository,
    @MainCoroutineDispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<Boolean> {
        return prefRepository.hasUserSeenOnBoarding().flowOn(dispatcher)
    }
}