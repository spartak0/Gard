package ru.spartak.gard.data.network.api

import retrofit2.Response
import retrofit2.http.*
import ru.spartak.gard.data.network.dto.BaseModel
import ru.spartak.gard.data.network.dto.DataModel

interface RetrofitApi {
    @GET("v2/stats/br/v2")
    suspend fun login(@Header("Authorization") apiKey: String , @Query("name") name: String): Response<BaseModel>
}