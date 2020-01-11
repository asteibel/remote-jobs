package com.lebdua.remotejobs.model.vo

data class Resource<out T>(
    val status: Status,
    val message: String,
    val data: T?
) {

    companion object {
        fun <T> success(data: T) = Resource(Status.SUCCESS, "", data)

        fun <T> error(message: String) = Resource<T>(Status.ERROR, message, null)

        fun <T> loading(message: String = "") = Resource<T>(Status.LOADING, message, null)
    }
}