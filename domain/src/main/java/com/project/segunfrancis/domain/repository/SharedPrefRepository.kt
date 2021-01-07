package com.project.segunfrancis.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface SharedPrefRepository {
    fun hasUserSeenOnBoarding(): Flow<Boolean>

    fun setUserHasSeenOnBoarding(value: Boolean): Flow<Unit>

    fun isBookmarked(date: String): Flow<Boolean>

    fun addBookmarkToPref(date: String): Flow<Unit>

    fun removeBookmarkFromPref(date: String): Flow<Unit>
}