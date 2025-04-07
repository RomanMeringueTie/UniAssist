package ru.sibsutis.uniassist.ui

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.sibsutis.authorization.di.AuthorizationComponent
import ru.sibsutis.student.di.StudentComponent
import ru.sibsutis.uniassist.navigation.BottomBar
import ru.sibsutis.uniassist.navigation.NavGraph
import ru.sibsutis.uniassist.presentation.MainActivityViewModel

@Composable
fun MainScreen(
    application: Application,
    mainViewModel: MainActivityViewModel,
    authorizationComponent: AuthorizationComponent,
    studentComponent: StudentComponent,
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
            studentComponent = studentComponent,
            isBottomBarShown = isBottomBarShown,
            paddingValues = paddingValues,
            authorizationComponent = authorizationComponent,
            startDestination = state.value,
            application = application
        )
    }
}