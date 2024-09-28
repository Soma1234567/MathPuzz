package com.soma.mathpuzz.domain.repository

import  kotlinx.coroutines.flow.Flow
interface DataStoreOperations {
   suspend fun saveLevel(level:Int)

    fun readLevel():Flow<Int>
}