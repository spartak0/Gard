package ru.spartak.gard.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.spartak.gard.data.network.dto.BaseModel
import ru.spartak.gard.data.network.dto.DataModel
import ru.spartak.gard.domain.model.NetworkResult

interface ApiRepository {
    fun getStats(accountName:String): Flow<NetworkResult<BaseModel>>
}