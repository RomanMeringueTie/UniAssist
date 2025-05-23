package ru.sibsutis.uniassist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ru.sibsutis.authorization.data.model.Role
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.authorization.di.AuthorizationComponent
import ru.sibsutis.core.di.CoreComponent

@Composable
fun NavGraph(
    navController: NavHostController,
    coreComponent: CoreComponent,
    authorizationComponent: AuthorizationComponent,
    isBottomBarShown: MutableState<Boolean>,
    modifier: Modifier,
    startDestination: Route
) {

    when (UserData.role.value) {

        Role.Student -> {
            StudentNavHost(
                navController = navController,
                coreComponent = coreComponent,
                isBottomBarShown = isBottomBarShown,
                modifier = modifier
            )
        }

        Role.Teacher -> {
            TeacherNavHost(
                navController = navController,
                coreComponent = coreComponent,
                isBottomBarShown = isBottomBarShown,
                modifier = modifier
            )
        }

        null -> {
            AuthorizationNavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = modifier,
                authorizationComponent = authorizationComponent
            )
        }
    }
}