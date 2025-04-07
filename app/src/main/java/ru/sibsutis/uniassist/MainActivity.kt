package ru.sibsutis.uniassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import ru.sibsutis.core.di.DaggerCoreComponent
import ru.sibsutis.uniassist.presentation.MainActivityViewModel
import ru.sibsutis.uniassist.ui.MainScreen
import ru.sibsutis.uniassist.ui.theme.UniAssistTheme

class MainActivity :
    ComponentActivity() {

    private val coreComponent by lazy { DaggerCoreComponent.builder().build() }



    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {

            UniAssistTheme {
                val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
                MainScreen(
                    mainViewModel = viewModel,
                    coreComponent = coreComponent,
                    application = application
                )
            }
        }
    }
}