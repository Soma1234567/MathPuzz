package com.soma.mathpuzz.domain.use_cases

import com.soma.mathpuzz.domain.use_cases.read_level_use_case.ReadLevelUseCase
import com.soma.mathpuzz.domain.use_cases.save_level_usecase.SaveLevelUseCase

data class UseCases(
    val readLevelUseCase: ReadLevelUseCase,
    val saveLevelUseCase: SaveLevelUseCase
)
