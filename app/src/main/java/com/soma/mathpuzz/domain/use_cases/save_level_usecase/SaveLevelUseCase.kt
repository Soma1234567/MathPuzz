package com.soma.mathpuzz.domain.use_cases.save_level_usecase

import com.soma.mathpuzz.data.repository.Repository

class SaveLevelUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(level:Int){
        repository.saveLevel(level)
    }
}