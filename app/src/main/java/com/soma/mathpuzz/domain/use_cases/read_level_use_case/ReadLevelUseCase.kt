package com.soma.mathpuzz.domain.use_cases.read_level_use_case

import com.soma.mathpuzz.data.repository.Repository
import kotlinx.coroutines.flow.Flow


class ReadLevelUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Int>{
        return repository.readLevel()
    }
}