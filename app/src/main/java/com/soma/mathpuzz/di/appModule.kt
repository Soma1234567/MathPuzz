package com.soma.mathpuzz.di

import com.soma.mathpuzz.data.repository.DataStoreOperationsImpl
import com.soma.mathpuzz.data.repository.Repository
import com.soma.mathpuzz.domain.repository.DataStoreOperations
import com.soma.mathpuzz.domain.use_cases.UseCases
import com.soma.mathpuzz.domain.use_cases.read_level_use_case.ReadLevelUseCase
import com.soma.mathpuzz.domain.use_cases.save_level_usecase.SaveLevelUseCase
import com.soma.mathpuzz.presentation.all_levels_screen.AllLevelViewModel
import com.soma.mathpuzz.presentation.home_screen.HomeViewModel
import com.soma.mathpuzz.presentation.level_screen.LevelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DataStoreOperations> {
        DataStoreOperationsImpl(get())
    }
    single<Repository> {
        Repository(get())
    }
    single<SaveLevelUseCase> {
        SaveLevelUseCase(get())
    }
    single<ReadLevelUseCase> {
        ReadLevelUseCase(get())
    }
    single<UseCases> {
        UseCases(get(),get())
    }
    viewModel<AllLevelViewModel>{
        AllLevelViewModel(get())
    }
    viewModel<LevelViewModel>{
        LevelViewModel(get())
    }
    viewModel<HomeViewModel>{
        HomeViewModel(get())
    }
}