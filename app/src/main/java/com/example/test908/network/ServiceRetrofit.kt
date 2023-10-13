package com.example.test908.network

import com.example.test908.data.model.Critic
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface serviceRetrofit {
    //Get all rewiewes
    @GET("reviews/search.json?")
    suspend fun getreview(
        @Query("api-key") api_key: String,
        @Query("page") position: Int

    ): Response<Critic>
    //Search
    @GET("reviews/search.json?")
    suspend fun getSearch(
        @Query("query") query: String?,
        @Query("api-key") api_key: String,
        @Query("page") position: Int
    ): Response<Critic>
}