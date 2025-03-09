package ru.sibsutis.uniassist.ui

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import ru.sibsutis.authorization.data.model.Role
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.authorization.di.AuthorizationComponent
import ru.sibsutis.student.di.StudentComponent
import ru.sibsutis.uniassist.navigation.Route

@Composable
fun NavGraph(
    navController: NavHostController,
    studentComponent: StudentComponent,
    isBottomBarShown: MutableState<Boolean>,
    paddingValues: PaddingValues,
    application: Application,
    authorizationComponent: AuthorizationComponent,
    startDestination: Route
) {
    val userRole = remember { mutableStateOf(UserData.role) }

    when (userRole.value) {

        Role.Student -> {
            StudentNavHost(
                navController = navController,
                studentComponent = studentComponent,
                isBottomBarShown = isBottomBarShown,
                paddingValues = paddingValues
            )
        }

        Role.Teacher -> {
            TeacherNavHost(
                navController = navController,
                isBottomBarShown = isBottomBarShown,
                paddingValues = paddingValues
            )
        }

        null -> {
            AuthorizationNavHost(
                navController = navController,
                startDestination = startDestination,
                paddingValues = paddingValues,
                authorizationComponent = authorizationComponent,
                application = application,
                role = userRole
            )
        }
    }
}