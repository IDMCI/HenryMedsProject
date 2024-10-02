package com.example.duncanclark.henrymedsproject.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.henrymedsproject.ui.component.Scheduler
import com.example.duncanclark.henrymedsproject.ui.viewmodel.UserScheduleViewModel
import kotlinx.datetime.LocalDate

@Composable
fun CalendarScreen(
    modifier: Modifier,
    navController: NavController = rememberNavController(),
    viewModel: UserScheduleViewModel = hiltViewModel()
) {
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val clientAppointment: LocalDate? = selectedDate.takeUnless {
        it == viewModel.localDateTime.date
    }

    Column (modifier) {
        Row() {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "Client Schedule: "
            )
            val appointmentToSchedule = clientAppointment ?: ""
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "$appointmentToSchedule",
                fontWeight = FontWeight.SemiBold,
            )
        }
        Text("Select date with provider:")
        Scheduler(modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()
        )
    }
}