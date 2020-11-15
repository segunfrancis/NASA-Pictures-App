package com.project.segunfrancis.nasapicturesapp.util

/**
 * Created by SegunFrancis
 */

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    class Error(val error: Throwable) : Result<Nothing>()
}