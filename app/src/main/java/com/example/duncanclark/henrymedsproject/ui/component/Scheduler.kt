package com.example.duncanclark.henrymedsproject.ui.component

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.duncanclark.henrymedsproject.ui.viewmodel.UserScheduleViewModel
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant

@Composable
fun Scheduler(
    modifier: Modifier,
    viewModel: UserScheduleViewModel = hiltViewModel()
) {
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    // This should logic should be moved and set in a UseCase.
    val today = viewModel.localDateTime
    val tomorrow: LocalDateTime = LocalDateTime(
        date = today.date.plus(1L, DateTimeUnit.DAY),
        time = today.time
    )

    Column(
        modifier = modifier
    ) {
        val context = LocalContext.current
        val datePicker = remember {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    viewModel.onDateSelected(LocalDate(year, month + 1, dayOfMonth))
                },
                selectedDate?.year ?: today.year,
                selectedDate?.monthNumber ?: today.monthNumber.minus(1),
                selectedDate?.dayOfMonth ?: today.dayOfMonth
            )
        }
        // This should logic should be moved and set in a UseCase.
        datePicker.datePicker.minDate = tomorrow.toInstant(
            TimeZone.currentSystemDefault()
        ).toEpochMilliseconds()

        Button(onClick = { datePicker.show()}) {
            Text(
                text = selectedDate?.toString() ?: "Select Date",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
        }
    }
}