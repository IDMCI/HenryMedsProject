package com.example.duncanclark.henrymedsproject.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.henrymedsproject.ui.state.UiState
import com.example.duncanclark.henrymedsproject.ui.viewmodel.UserScheduleViewModel

@Composable
fun UserScreen(
    modifier: Modifier,
    navController: NavController = rememberNavController(),
    viewModel: UserScheduleViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier) {
        when (uiState) {
            is UiState.Error -> Text((uiState as UiState.Error).message)
            UiState.Idle -> Text("TESTING: IDLE")
            UiState.Loading -> Text("TESTING: LOADING")
            is UiState.Success -> Text((uiState as UiState.Success).data)
        }
    }
}