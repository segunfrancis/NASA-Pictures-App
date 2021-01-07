package com.project.segunfrancis.domain.usecase

import com.project.segunfrancis.domain.repository.SharedPrefRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SetUserHasSeenOnBoardingUseCase @Inject constructor(
    private val prefRepository: SharedPrefRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(value: Boolean): Flow<Unit> {
        return prefRepository.setUserHasSeenOnBoarding(value).flowOn(dispatcher)
    }
}