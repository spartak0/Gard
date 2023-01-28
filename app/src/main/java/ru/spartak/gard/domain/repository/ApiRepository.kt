package ru.spartak.gard.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.spartak.gard.data.network.dto.Data
import ru.spartak.gard.domain.model.DataModel
import ru.spartak.gard.domain.model.NetworkResult

interface ApiRepository {
    fun getStats(accountId:String): Flow<NetworkResult<DataModel>>
    fun getAccountId(accountName:String): Flow<NetworkResult<String>>
}