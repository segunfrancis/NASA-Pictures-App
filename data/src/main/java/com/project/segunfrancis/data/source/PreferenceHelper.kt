package com.project.segunfrancis.data.source

import kotlinx.coroutines.flow.Flow

interface PreferenceHelper {

    fun hasUserSeenOnBoarding(): Flow<Boolean>

    fun setUserHasSeenOnBoarding(value: Boolean): Flow<Unit>

    fun addBookmarkToSharedPref(date: String): Flow<Unit>

    fun removeBookmarkFromSharedPref(date: String): Flow<Unit>

    fun isBookmarked(date: String): Flow<Boolean>
}
