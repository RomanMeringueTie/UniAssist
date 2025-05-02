package ru.sibsutis.uniassist.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.sibsutis.core.di.CoreComponent
import ru.sibsutis.core.utils.daggerViewModel
import ru.sibsutis.teacher.di.DaggerTeacherComponent
import ru.sibsutis.teacher.presentation.TeacherClassViewModel
import ru.sibsutis.teacher.ui.TeacherClassScreen
import ru.sibsutis.teacher.ui.TeacherProfileScreen
import ru.sibsutis.teacher.ui.TeacherScheduleScreen

@Composable
fun TeacherNavHost(
    navController: NavHostController,
    coreComponent: CoreComponent,
    isBottomBarShown: MutableState<Boolean>,
    modifier: Modifier,
) {
    val teacherComponent by lazy {
        DaggerTeacherComponent.builder().coreComponent(coreComponent).build()
    }

    NavHost(
        navController = navController,
        startDestination = Route.ScheduleRoute,
        modifier = modifier
    ) {
        composable<Route.ScheduleRoute> {
            val viewModel = daggerViewModel(key = "ScheduleViewModel") { teacherComponent.getScheduleViewModel() }
            TeacherScheduleScreen(viewModel) { id ->
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
            TeacherProfileScreen()
        }
        composable<Route.ClassRoute> { backStackEntry ->
            val getTeacherClassUseCase =
                teacherComponent.getTeacherClassUseCase()
            val classConverter = teacherComponent.getClassConverter()
            val id = backStackEntry.toRoute<Route.ClassRoute>().id
            val viewModel: TeacherClassViewModel =
                daggerViewModel (key = "TeacherClassViewModel-$id") {
                    TeacherClassViewModel(
                        classConverter,
                        getTeacherClassUseCase,
                        id
                    )
                }
            TeacherClassScreen(
                viewModel = viewModel
            )
        }
    }
}