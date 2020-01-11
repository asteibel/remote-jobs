package com.lebdua.remotejobs.networking.services

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("/api")
    fun getJobList(): Flowable<JsonArray>
}
