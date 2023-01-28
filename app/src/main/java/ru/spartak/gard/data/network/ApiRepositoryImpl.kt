package ru.spartak.gard.data.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.spartak.gard.data.network.api.RetrofitApi
import ru.spartak.gard.domain.mapper.StatsMapper
import ru.spartak.gard.domain.model.DataModel
import ru.spartak.gard.domain.model.NetworkResult
import ru.spartak.gard.domain.repository.ApiRepository
import ru.spartak.gard.utils.Constant

class ApiRepositoryImpl(private val api: RetrofitApi, private val statsMapper: StatsMapper) :
    ApiRepository {
    override fun getStats(accountId: String): Flow<NetworkResult<DataModel>> = flow {
        val response = api.getStats(apiKey = Constant.API_KEY, accountId = accountId)
        if (response.isSuccessful) {
            response.body()?.let { data ->
                emit(NetworkResult.Success(statsMapper.toDomain(data)))
            }
        } else emit(NetworkResult.Error(response.message()))

    }

    override fun getAccountId(accountName: String): Flow<NetworkResult<String>> = flow {
        val response = api.getAccountId(apiKey = Constant.API_KEY, accountName = accountName)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(NetworkResult.Success(it.toString()))
            }
        } else emit(NetworkResult.Error(response.message()))
    }
}