package ru.sibsutis.uniassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import ru.sibsutis.authorization.di.DaggerAuthorizationComponent
import ru.sibsutis.core.di.DaggerCoreComponent
import ru.sibsutis.student.di.DaggerStudentComponent
import ru.sibsutis.uniassist.presentation.MainActivityViewModel
import ru.sibsutis.uniassist.ui.MainScreen
import ru.sibsutis.uniassist.ui.theme.UniAssistTheme

class MainActivity :
    ComponentActivity() {

    private val coreComponent by lazy { DaggerCoreComponent.builder().build() }
    private val studentComponent by lazy {
        DaggerStudentComponent.builder().coreComponent(coreComponent).build()
    }
    private val authorizationComponent by lazy {
        DaggerAuthorizationComponent.builder().coreComponent(coreComponent).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val isBottomBarShown = rememberSaveable { mutableStateOf(false) }
            UniAssistTheme {
                val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
                MainScreen(
                    application = application,
                    mainViewModel = viewModel,
                    authorizationComponent = authorizationComponent,
                    studentComponent = studentComponent,
                    navController = navController,
                    isBottomBarShown = isBottomBarShown
                )
            }
        }
    }
}