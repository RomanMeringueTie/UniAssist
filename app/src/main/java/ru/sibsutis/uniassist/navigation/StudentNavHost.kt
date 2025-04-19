package ru.sibsutis.uniassist.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.core.di.CoreComponent
import ru.sibsutis.core.utils.daggerViewModel
import ru.sibsutis.student.di.DaggerStudentComponent
import ru.sibsutis.student.presentation.StudentClassViewModel
import ru.sibsutis.student.ui.StudentClassScreen
import ru.sibsutis.student.ui.StudentScheduleScreen

@Composable
fun StudentNavHost(
    navController: NavHostController,
    coreComponent: CoreComponent,
    isBottomBarShown: MutableState<Boolean>,
    modifier: Modifier,
) {
    val studentComponent by lazy {
        DaggerStudentComponent.builder().coreComponent(coreComponent).build()
    }

    NavHost(
        navController = navController,
        startDestination = Route.ScheduleRoute,
        modifier = modifier
    ) {
        composable<Route.ScheduleRoute> {
            val viewModel =
                daggerViewModel(key = "ScheduleViewModel") { studentComponent.getScheduleViewModel() }
            StudentScheduleScreen(viewModel) { id ->
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