package ru.spartak.gard.data.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.spartak.gard.data.network.api.RetrofitApi
import ru.spartak.gard.data.network.dto.BaseModel
import ru.spartak.gard.data.network.dto.DataModel
import ru.spartak.gard.domain.model.NetworkResult
import ru.spartak.gard.domain.repository.ApiRepository
import ru.spartak.gard.utils.Constant

class ApiRepositoryImpl(private val api:RetrofitApi):ApiRepository {
    override fun getStats(accountName: String):  Flow<NetworkResult<BaseModel>> = flow{
        try {
            val response = api.login( apiKey = Constant.API_KEY, name = accountName)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(NetworkResult.Success(it))
                }
            }
            else emit(NetworkResult.Error(response.message()))
        }
        catch (e:Exception){
            emit(NetworkResult.Error(e.message))
        }

    }
}