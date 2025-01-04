package ru.sibsutis.uniassist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.core.utils.daggerViewModel
import ru.sibsutis.student.data.repository.StudentRepository
import ru.sibsutis.student.data.repository.StudentRepositoryImpl
import ru.sibsutis.student.data.service.StudentService
import ru.sibsutis.student.di.DaggerStudentComponent
import ru.sibsutis.student.domain.GetStudentClassUseCase
import ru.sibsutis.student.presentation.StudentClassViewModel
import ru.sibsutis.student.ui.StudentClassScreen
import ru.sibsutis.student.ui.StudentScheduleScreen
import ru.sibsutis.uniassist.navigation.BottomBar
import ru.sibsutis.uniassist.navigation.Route
import ru.sibsutis.uniassist.ui.theme.UniAssistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            UniAssistTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
                    bottomBar = {
                        BottomBar(
                            navController = navController
                        )
                    }) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.ScheduleRoute,
                        modifier = Modifier.padding(it)
                    ) {
                        composable<Route.ScheduleRoute> {
                            val component = DaggerStudentComponent.builder().build()
                            val viewModel =
                                daggerViewModel(key = "ScheduleViewModel") { component.getScheduleViewModel() }
                            StudentScheduleScreen(viewModel) { id: Int ->
                                navController.navigate(
                                    Route.ClassRoute(id)
                                )
                            }
                        }
                        composable<Route.MessageRoute> {
                            Text(text = "Messages")
                        }
                        composable<Route.ProfileRoute> {
                            Text(text = "Profile")
                        }
                        composable<Route.ClassRoute> { backStackEntry ->
                            val id = backStackEntry.toRoute<Route.ClassRoute>().id
                            StudentClassScreen(
                                viewModel = StudentClassViewModel(
                                    GetStudentClassUseCase(
                                        StudentRepositoryImpl(
                                            StudentService(
                                                KtorClient()
                                            )
                                        )
                                    ),
                                    id = id
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}