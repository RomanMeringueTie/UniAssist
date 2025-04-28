package ru.sibsutis.uniassist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.sibsutis.authorization.di.AuthorizationComponent
import ru.sibsutis.authorization.ui.AuthorizationScreen
import ru.sibsutis.authorization.ui.BackgroundAuthorizationScreen
import ru.sibsutis.core.utils.daggerViewModel


@Composable
fun AuthorizationNavHost(
    navController: NavHostController,
    startDestination: Route,
    modifier: Modifier,
    authorizationComponent: AuthorizationComponent
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable<Route.AuthorizationRoute> {
            // TODO("Заменить на фабрику viewModel")
            val viewModel =
                daggerViewModel(key = "AuthorizationViewModel") {
                    authorizationComponent.getAuthorizationViewModel()
                }
            AuthorizationScreen(
                viewModel = viewModel
            )
        }
        composable<Route.BackgroundAuthorizationRoute> {
            // TODO("Заменить на фабрику viewModel")
            val viewModel =
                daggerViewModel(key = "BackgroundAuthorizationViewModel") {
                    authorizationComponent.getBackgroundAuthorizationViewModel()
                }
            BackgroundAuthorizationScreen(
                viewModel = viewModel,
                onFailure = {
                    navController.navigate(Route.AuthorizationRoute)
                }
            )
        }
    }
}