package com.santimattius.template.core.data

sealed class Result<out T : Any> {

    companion object {

        fun <T : Any> success(out: T) = Success(out)

        fun failure(exception: AppException) = Failure(exception)
    }
}

data class Success<T : Any> internal constructor(val out: T) : Result<T>()

data class Failure(val exception: AppException) : Result<Nothing>()