package com.soma.mathpuzz.presentation.all_levels_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.mathpuzz.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AllLevelViewModel(
    useCases: UseCases
):ViewModel() {

    private val _levelsCompleted = MutableStateFlow(-1)
    val levelsCompleted: StateFlow<Int> = _levelsCompleted.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _levelsCompleted.value = useCases.readLevelUseCase().stateIn(viewModelScope).value
            //stateIn() function is used to convert flow to StateFlow
        }
    }
}