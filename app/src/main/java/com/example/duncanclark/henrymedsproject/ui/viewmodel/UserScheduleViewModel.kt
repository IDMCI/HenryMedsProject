package com.example.duncanclark.henrymedsproject.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
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
    private val useCase: SetAvailabilityUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val uiState: StateFlow<UiState<String>> = _uiState

    val localDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    private val _selectedDate = MutableStateFlow<LocalDate?>(null
//        LocalDate(
//            localDateTime.year,
//            localDateTime.month,
//            localDateTime.dayOfMonth,
//        )
    )
    val selectedDate: MutableStateFlow<LocalDate?> = _selectedDate

    init {
        // Get savedState here and set it to _uiState.value

        val dummyUser = DummyClientData()
        setInitialSchedule(dummyUser.user, dummyUser.userSchedule)
    }

    fun onDateSelected(date: LocalDate) {
        _selectedDate.value = date
    }

    private fun setInitialSchedule(user: User, schedule: UserSchedule) {

        viewModelScope.launch {
            useCase.execute(user, schedule, null).collect { result ->
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

    // Base dummy data to get started.
    inner class DummyClientData() {
        private val userId: Long = 2

        val user: User
            get() = User(
                id = userId,
                userType = UserType.CLIENT
            )
        val userSchedule: UserSchedule
            get() = UserSchedule(
                userId = userId,
                schedule = emptyList(),
                availabilitySchedule = emptyList()
            )
    }

    // Base dummy data to get started.
    inner class DummyProviderData() {
        // Initial data based upon requirements
        private val specialDate = LocalDate(2024, 8, 13)
        private val availableTimeStart = LocalTime(8, 0, 0)
        private val availableTimeEnd = LocalTime(15, 0, 0)
        private val userId: Long = 7

        val user: User
            get() = User(
                id = userId,
                userType = UserType.PROVIDER,
            )

        val userSchedule: UserSchedule
            get() = UserSchedule(
                userId = userId,
                schedule = emptyList(),
                availabilitySchedule = listOf(
                    Event(
                        startTime = LocalDateTime(specialDate, availableTimeStart),
                        endTime = LocalDateTime(specialDate, availableTimeEnd),
                        null,
                        null,
                    )
                )
            )
    }
}