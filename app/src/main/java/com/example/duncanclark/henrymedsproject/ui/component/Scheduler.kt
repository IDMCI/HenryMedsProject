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
import kotlinx.datetime.LocalDate

@Composable
fun Scheduler(
    modifier: Modifier,
    viewModel: UserScheduleViewModel = hiltViewModel()
) {
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val defaultDateOfTomorrow = viewModel.localDateTime

    Column(
        modifier = modifier
    ) {
        val context = LocalContext.current
        val dataPicker = remember {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    viewModel.onDateSelected(LocalDate(year, month + 1, dayOfMonth))
                },
                selectedDate?.year ?: defaultDateOfTomorrow.year,
                selectedDate?.monthNumber ?: defaultDateOfTomorrow.monthNumber.minus(1),
                selectedDate?.dayOfMonth ?: defaultDateOfTomorrow.dayOfMonth
            )
        }
        Button(onClick = { dataPicker.show()}) {
            Text(
                text = selectedDate?.toString() ?: "Select Date",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
        }
    }
}