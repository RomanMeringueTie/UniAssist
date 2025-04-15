package ru.sibsutis.uniassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.sibsutis.authorization.di.DaggerAuthorizationComponent
import ru.sibsutis.core.di.DaggerCoreComponent
import ru.sibsutis.core.utils.daggerViewModel
import ru.sibsutis.uniassist.di.DaggerAppComponent
import ru.sibsutis.uniassist.ui.MainScreen
import ru.sibsutis.uniassist.ui.theme.UniAssistTheme

class MainActivity :
    ComponentActivity() {

    private val coreComponent by lazy {
        DaggerCoreComponent.builder().build()
    }

    private val authorizationComponent by lazy {
        DaggerAuthorizationComponent.builder().coreComponent(coreComponent)
            .context(applicationContext)
            .build()
    }

    private val appComponent by lazy {
        DaggerAppComponent.builder().coreComponent(coreComponent)
            .authorizationComponent(authorizationComponent).build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            UniAssistTheme {
                val viewModel =
                    daggerViewModel(key = "ScheduleViewModel") { appComponent.getMainActivityViewModel() }
                MainScreen(
                    mainViewModel = viewModel,
                    coreComponent = coreComponent,
                    authorizationComponent = authorizationComponent
                )
            }
        }
    }
}