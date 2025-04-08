package ru.sibsutis.uniassist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.sibsutis.authorization.di.AuthorizationComponent
import ru.sibsutis.core.di.CoreComponent
import ru.sibsutis.uniassist.navigation.BottomBar
import ru.sibsutis.uniassist.navigation.NavGraph
import ru.sibsutis.uniassist.presentation.MainActivityViewModel

@Composable
fun MainScreen(
    mainViewModel: MainActivityViewModel,
    coreComponent: CoreComponent,
    authorizationComponent: AuthorizationComponent
) {
    val navController = rememberNavController()
    val isBottomBarShown = rememberSaveable { mutableStateOf(false) }
    val state = mainViewModel.startDestination.collectAsState()

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
            isBottomBarShown = isBottomBarShown,
            modifier = Modifier.padding(paddingValues),
            coreComponent = coreComponent,
            startDestination = state.value,
            authorizationComponent = authorizationComponent
        )
    }
}