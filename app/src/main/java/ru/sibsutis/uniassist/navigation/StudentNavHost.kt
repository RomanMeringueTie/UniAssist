//package ru.sibsutis.uniassist.navigation
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.toRoute
//import ru.sibsutis.authorization.ui.AuthorizationScreen
//import ru.sibsutis.core.utils.daggerViewModel
//import ru.sibsutis.student.presentation.StudentClassViewModel
//import ru.sibsutis.student.ui.StudentClassScreen
//import ru.sibsutis.student.ui.StudentScheduleScreen
//
//@Composable
//fun StudentNavHost() {
//    NavHost(
//        navController = navController,
//        startDestination = Route.AuthorizationRoute,
//        modifier = Modifier.padding(it)
//    ) {
//        composable<Route.AuthorizationRoute> {
//            val viewModel =
//                daggerViewModel(key = "AuthorizationViewModel") {
//                    authorizationComponent.getAuthorizationViewModel()
//                }
//            AuthorizationScreen(
//                viewModel = viewModel,
//                onClick = {
//                    navController.navigate(Route.ScheduleRoute) {
//                        popUpTo<Route.AuthorizationRoute> {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }
//        composable<Route.ScheduleRoute> {
//            val viewModel =
//                daggerViewModel(key = "ScheduleViewModel") { studentComponent.getScheduleViewModel() }
//            StudentScheduleScreen(viewModel) { id: Int ->
//                navController.navigate(
//                    Route.ClassRoute(id)
//                )
//            }
//            isBottomBarShown = true
//        }
//        composable<Route.MessageRoute> {
//            Text(text = "Messages")
//        }
//        composable<Route.ProfileRoute> {
//            Text(text = "Profile")
//        }
//        composable<Route.ClassRoute> { backStackEntry ->
//            val getStudentClassUseCase =
//                studentComponent.getGetStudentClassUseCase()
//            val classConverter = studentComponent.getClassConverter()
//            val id = backStackEntry.toRoute<Route.ClassRoute>().id
//            // TODO("Заменить на фабрику viewModel")
//            val viewModel: StudentClassViewModel =
//                daggerViewModel(key = "StudentClassViewModel-$id") {
//                    StudentClassViewModel(
//                        classConverter,
//                        getStudentClassUseCase,
//                        id
//                    )
//                }
//            StudentClassScreen(
//                viewModel = viewModel
//            )
//        }
//}