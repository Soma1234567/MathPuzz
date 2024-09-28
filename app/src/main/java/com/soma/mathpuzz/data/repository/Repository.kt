package com.soma.mathpuzz.data.repository

import com.soma.mathpuzz.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow

class Repository(
    private val dataStoreOperations: DataStoreOperations
) {
    suspend fun saveLevel(level:Int){
        dataStoreOperations.saveLevel(level)
    }

    fun readLevel():Flow<Int>{
        return dataStoreOperations.readLevel()
    }
}