package com.example.androidmoviedb.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers


fun <T, A> getCall (
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
) : LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {

        emit(Resource.loading())

        val source = databaseQuery.invoke().map { Resource.success(it) }

        val response = networkCall.invoke()
        if (response.status == Resource.Status.SUCCESS) {
            saveCallResult(response.data!!)
        } else if (response.status == Resource.Status.ERROR) {
            emit(Resource.error(response.message!!))
            emitSource(source)
        }
    }