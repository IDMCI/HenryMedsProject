package com.example.duncanclark.henrymedsproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.henrymedsproject.ui.screen.CalendarScreen
import com.example.duncanclark.henrymedsproject.ui.screen.ExampleScreen
import com.example.duncanclark.henrymedsproject.ui.theme.HenryMedsProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HenryMedsProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.reservations)) }
                        )
                    }
                ) { innerPadding ->
                    MainActivityPrompt(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainActivityPrompt(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "schedule"
    ) {
        composable("schedule") {
            CalendarScreen(
                modifier
                    .fillMaxSize()
                    .padding(18.dp)
            )
        }
        composable("example/{id}") {
            ExampleScreen(
                modifier = modifier
                    .fillMaxSize()
                    .padding(18.dp)
            )
        }
    }
}