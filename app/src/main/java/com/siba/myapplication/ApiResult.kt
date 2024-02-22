package com.siba.myapplication


sealed class ApiResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ApiResult<T>(data)
    class Error<T>(message: String? = null, data: T? = null) : ApiResult<T>(data, message)
    class Loading<T>(val isLoading: Boolean) : ApiResult<T>()
}
