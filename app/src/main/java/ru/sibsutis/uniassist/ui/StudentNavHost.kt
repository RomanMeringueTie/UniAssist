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
import androidx.navigation.toRoute
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.core.utils.daggerViewModel
import ru.sibsutis.student.di.StudentComponent
import ru.sibsutis.student.presentation.StudentClassViewModel
import ru.sibsutis.student.ui.StudentClassScreen
import ru.sibsutis.student.ui.StudentScheduleScreen
import ru.sibsutis.uniassist.navigation.Route

@Composable
fun StudentNavHost(
    navController: NavHostController,
    studentComponent: StudentComponent,
    isBottomBarShown: MutableState<Boolean>,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = Route.ScheduleRoute,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable<Route.ScheduleRoute> {
            val viewModel =
                daggerViewModel(key = "ScheduleViewModel") { studentComponent.getScheduleViewModel() }
            StudentScheduleScreen(viewModel) { id: Int ->
                navController.navigate(
                    Route.ClassRoute(id)
                )
            }
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