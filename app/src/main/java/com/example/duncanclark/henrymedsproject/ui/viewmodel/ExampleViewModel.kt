package com.example.duncanclark.henrymedsproject.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duncanclark.henrymedsproject.datasource.model.Client
import com.example.duncanclark.henrymedsproject.domain.model.Event
import com.example.duncanclark.henrymedsproject.domain.model.Schedule
import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import com.example.duncanclark.henrymedsproject.domain.model.UserType
import com.example.duncanclark.henrymedsproject.domain.usecase.ExampleGetScheduleUseCase
import com.example.duncanclark.henrymedsproject.domain.usecase.SetAvailabilityUseCase
import com.example.duncanclark.henrymedsproject.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val useCase: ExampleGetScheduleUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val uiState: StateFlow<UiState<String>> = _uiState

    // Example of how to get current date.
    private val localDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    // This should happen after authentication and user data would be cached locally.
    init {
        val userId: Long? = savedStateHandle["id"]
        userId?.let {
            val dummyUser = User(it, UserType.UNKNOWN)
            getSchedule(dummyUser)
        }
    }

    private fun getSchedule(user: User) {
        viewModelScope.launch {
            useCase.execute(user).collect { result ->
                _uiState.value = when {
                    result.isSuccess -> {
                        val message = "Testing... is a SUCCESS!!!"
                        UiState.Success(message)
                    }
                    result.isFailure -> {
                        val message = "Testing... FAILED!!!"
                        UiState.Error(result.exceptionOrNull()?.message ?: message)
                    }

                    else -> { UiState.Error("Oops!")}
                }
            }
        }
    }
}