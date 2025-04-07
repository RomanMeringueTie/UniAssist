package ru.sibsutis.uniassist.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.sibsutis.authorization.data.model.Role
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.authorization.di.DaggerAuthorizationComponent
import ru.sibsutis.authorization.presentation.AuthorizationViewModel
import ru.sibsutis.authorization.presentation.BackgroundAuthorizationViewModel
import ru.sibsutis.authorization.ui.AuthorizationScreen
import ru.sibsutis.authorization.ui.BackgroundAuthorizationScreen
import ru.sibsutis.core.di.CoreComponent
import ru.sibsutis.core.utils.daggerViewModel


@Composable
fun AuthorizationNavHost(
    navController: NavHostController,
    startDestination: Route,
    modifier: Modifier,
    coreComponent: CoreComponent,
    application: Application,
) {

    val authorizationComponent by lazy {
        DaggerAuthorizationComponent.builder().coreComponent(coreComponent).build()
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
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
                onFailure = {
                    navController.navigate(Route.AuthorizationRoute)
                }
            )
        }
    }
}