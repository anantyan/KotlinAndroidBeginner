package com.tutorial.api

import com.tutorial.BuildConfig
import com.tutorial.models.ResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("/3/discover/movie")
    fun discoverMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Single<ResponseModel>
}