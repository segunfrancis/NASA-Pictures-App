package com.project.segunfrancis.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface SharedPrefRepository {
    fun isBookmarked(date: String): Flow<Boolean>

    fun addBookmarkToPref(date: String): Flow<Unit>

    fun removeBookmarkFromPref(date: String): Flow<Unit>
}