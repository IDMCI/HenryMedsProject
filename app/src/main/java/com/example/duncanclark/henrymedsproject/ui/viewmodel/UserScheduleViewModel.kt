package com.example.duncanclark.henrymedsproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duncanclark.henrymedsproject.domain.model.Event
import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import com.example.duncanclark.henrymedsproject.domain.model.UserType
import com.example.duncanclark.henrymedsproject.domain.usecase.SetAvailabilityUseCase
import com.example.duncanclark.henrymedsproject.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

@HiltViewModel
class UserScheduleViewModel @Inject constructor(
    private val useCase: SetAvailabilityUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val uiState: StateFlow<UiState<String>> = _uiState

    init {
        val clientUser = User(
            id = 2,
            userType = UserType.CLIENT
        )
        val providerUser = User(
            id = 7,
            userType = UserType.PROVIDER
        )
        val localDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val specialDate = LocalDate(2024, 8, 13)
        val availableTimeStart = LocalTime(8, 0, 0)
        val availableTimeEnd = LocalTime(15, 0, 0)

        val providerSchedule = UserSchedule(
            userId = 7,
            schedule = emptyList<Event>(),
            availabilitySchedule = listOf(
                Event(
                    startTime = LocalDateTime(specialDate, availableTimeStart),
                    endTime = LocalDateTime(specialDate, availableTimeEnd),
                    null,
                    null,
                )
            )
        )
        setInitialSchedule(providerUser, providerSchedule)
    }

    private fun setInitialSchedule(user: User, schedule: UserSchedule) {

        viewModelScope.launch {
            useCase.execute(user, schedule, null).collect { result ->
                _uiState.value = when {
                    result.isSuccess -> {
                        val message = "Testing... SUCCESS!!!"
                        UiState.Success(message)
                    }
                    result.isFailure -> {
                        val message = "Testing... FAILED!!!"
                        UiState.Error(message)
                    }

                    else -> { UiState.Error("Oops!")}
                }

            }
        }
    }
}