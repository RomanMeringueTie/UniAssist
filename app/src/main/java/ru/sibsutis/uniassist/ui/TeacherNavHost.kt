package ru.sibsutis.uniassist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.uniassist.navigation.Route

@Composable
fun TeacherNavHost(
    navController: NavHostController,
    isBottomBarShown: MutableState<Boolean>,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = Route.ScheduleRoute,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable<Route.ScheduleRoute> {
            Text("Schedule")
            isBottomBarShown.value = true
        }
        composable<Route.MessageRoute> {
            Text(text = "Messages")
        }
        composable<Route.ProfileRoute> {
            Column(modifier = Modifier.fillMaxSize()) {
                UserData.apply {
                    Text(text = fullName ?: "NoName")
                    Text(text = unit ?: "NoUnit")
                }
            }
        }
        composable<Route.ClassRoute> {
            Text("Class")
        }
    }
}