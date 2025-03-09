package ru.sibsutis.uniassist.ui

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ru.sibsutis.authorization.di.AuthorizationComponent
import ru.sibsutis.student.di.StudentComponent
import ru.sibsutis.uniassist.navigation.BottomBar
import ru.sibsutis.uniassist.navigation.Route
import ru.sibsutis.uniassist.presentation.MainActivityViewModel

@Composable
fun MainScreen(
    application: Application,
    mainViewModel: MainActivityViewModel,
    authorizationComponent: AuthorizationComponent,
    studentComponent: StudentComponent,
    navController: NavHostController,
    isBottomBarShown: MutableState<Boolean>
) {

    val isAuthorized = mainViewModel.isAuthorized.collectAsState()
    val startDestination by lazy {
        if (!isAuthorized.value) Route.AuthorizationRoute else Route.BackgroundAuthorizationRoute(
            login = mainViewModel.login ?: "",
            password = mainViewModel.password ?: ""
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        bottomBar = {
            if (isBottomBarShown.value)
                BottomBar(
                    navController = navController
                )
        }) { paddingValues ->
        NavGraph(
            navController = navController,
            studentComponent = studentComponent,
            isBottomBarShown = isBottomBarShown,
            paddingValues = paddingValues,
            application = application,
            authorizationComponent = authorizationComponent,
            startDestination = startDestination
        )
    }
}