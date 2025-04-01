package ru.sibsutis.uniassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ru.sibsutis.core.di.DaggerCoreComponent
import ru.sibsutis.core.utils.daggerViewModel
import ru.sibsutis.student.di.DaggerStudentComponent
import ru.sibsutis.student.presentation.StudentClassViewModel
import ru.sibsutis.student.ui.StudentClassScreen
import ru.sibsutis.student.ui.StudentScheduleScreen
import ru.sibsutis.teacher.di.DaggerTeacherComponent
import ru.sibsutis.teacher.ui.TeacherScheduleScreen
import ru.sibsutis.uniassist.navigation.BottomBar
import ru.sibsutis.uniassist.navigation.Route
import ru.sibsutis.uniassist.ui.theme.UniAssistTheme

class MainActivity : ComponentActivity() {
    private val coreComponent by lazy { DaggerCoreComponent.builder().build() }
    private val studentComponent by lazy {
        DaggerStudentComponent.builder().coreComponent(coreComponent).build()
    }
    private val teacherComponent by lazy {
        DaggerTeacherComponent.builder().coreComponent(coreComponent).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            if (true) {

                UniAssistTheme {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding(),
                        bottomBar = { BottomBar(navController = navController) }) {
                        NavHost(
                            navController = navController,
                            startDestination = Route.ScheduleRoute,
                            modifier = Modifier.padding(it)
                        ) {
                            composable<Route.ScheduleRoute> {
                                val viewModel =
                                    daggerViewModel(key = "ScheduleViewModel") { studentComponent.getScheduleViewModel() }
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
                                val getStudentClassUseCase =
                                    studentComponent.getGetStudentClassUseCase()
                                val classConverter = studentComponent.getClassConverter()
                                val id = backStackEntry.toRoute<Route.ClassRoute>().id
                                // TODO("Заменить на фабрику viewModel")
                                val viewModel: StudentClassViewModel =
                                    daggerViewModel(key = "StudentClassViewModel-$id") {
                                        StudentClassViewModel(
                                            classConverter,
                                            getStudentClassUseCase,
                                            id
                                        )
                                    }
                                StudentClassScreen(
                                    viewModel = viewModel
                                )
                            }

                        }
                    }
                }
            } else {

                UniAssistTheme {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding(),
                        bottomBar = { BottomBar(navController = navController) }) {
                        NavHost(
                            navController = navController,
                            startDestination = Route.ScheduleRoute,
                            modifier = Modifier.padding(it)
                        ) {
                            composable<Route.ScheduleRoute> {
                                val teacherScheduleViewModel =
                                    daggerViewModel(key = "ScheduleViewModel") { teacherComponent.getScheduleViewModel() }
                                TeacherScheduleScreen(teacherScheduleViewModel)
                            }
                            composable<Route.MessageRoute> {
                                Text(text = "Messages")
                            }
                            composable<Route.ProfileRoute> {
                                Text(text = "Profile")
                            }
                        }
                    }
                }
            }
        }
    }
}