package ru.sibsutis.uniassist.navigation

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.sibsutis.authorization.data.model.Role
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.authorization.di.AuthorizationComponent
import ru.sibsutis.authorization.presentation.AuthorizationViewModel
import ru.sibsutis.authorization.presentation.BackgroundAuthorizationViewModel
import ru.sibsutis.authorization.ui.AuthorizationScreen
import ru.sibsutis.authorization.ui.BackgroundAuthorizationScreen
import ru.sibsutis.core.utils.daggerViewModel


@Composable
fun AuthorizationNavHost(
    navController: NavHostController,
    startDestination: Route,
    paddingValues: PaddingValues,
    authorizationComponent: AuthorizationComponent,
    application: Application,
    role: MutableState<Role?>
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable<Route.AuthorizationRoute> {
            val getTokenUseCase =
                authorizationComponent.getTokenUseCase()
            // TODO("Заменить на фабрику viewModel")
            val viewModel =
                daggerViewModel(key = "AuthorizationViewModel") {
                    AuthorizationViewModel(
                        getTokenUseCase = getTokenUseCase,
                        application = application
                    )
                }
            AuthorizationScreen(
                viewModel = viewModel,
                onClick = {
                    role.value = UserData.role
                }
            )
        }
        composable<Route.BackgroundAuthorizationRoute> {
            val getTokenUseCase =
                authorizationComponent.getTokenUseCase()
            // TODO("Заменить на фабрику viewModel")
            val viewModel =
                daggerViewModel(key = "BackgroundAuthorizationViewModel") {
                    BackgroundAuthorizationViewModel(
                        getTokenUseCase = getTokenUseCase,
                        application = application
                    )
                }
            BackgroundAuthorizationScreen(
                viewModel = viewModel,
                onContent = {
                    role.value = UserData.role
                },
                onFailure = {
                    navController.navigate(Route.AuthorizationRoute)
                }
            )
        }
    }
}