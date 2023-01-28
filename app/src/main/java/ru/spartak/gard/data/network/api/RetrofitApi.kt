package ru.spartak.gard.data.network.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.spartak.gard.data.network.dto.Data

interface RetrofitApi {
    @GET("v1/stats")
    suspend fun getStats(@Header("Authorization") apiKey: String, @Query("account") accountId:String): Response<Data>

    @GET("v1/lookup")
    suspend fun getAccountId(@Header("Authorization") apiKey: String , @Query("username") accountName:String): Response<JsonObject>
}