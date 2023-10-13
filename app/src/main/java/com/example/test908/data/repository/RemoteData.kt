package com.example.test908.data.repository

import com.example.test908.network.serviceRetrofit
import javax.inject.Inject

class remoteData @Inject constructor(private val service: serviceRetrofit) {
    suspend fun getUser(api:String,position:Int)= service.getreview(api,position)
}