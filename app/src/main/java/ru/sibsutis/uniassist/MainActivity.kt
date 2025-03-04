package ru.sibsutis.uniassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.sibsutis.core.di.DaggerCoreComponent
import ru.sibsutis.core.utils.daggerViewModel
import ru.sibsutis.teacher.di.DaggerTeacherComponent
import ru.sibsutis.teacher.ui.TeacherScheduleScreen
import ru.sibsutis.uniassist.navigation.BottomBar
import ru.sibsutis.uniassist.navigation.MESSAGES_ROUTE
import ru.sibsutis.uniassist.navigation.PROFILE_ROUTE
import ru.sibsutis.uniassist.navigation.SCHEDULE_ROUTE
import ru.sibsutis.uniassist.ui.theme.UniAssistTheme

class MainActivity : ComponentActivity() {
    private val coreComponent by lazy { DaggerCoreComponent.builder().build() }
    private val teacherComponent by lazy {
        DaggerTeacherComponent.builder().coreComponent(coreComponent).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()


            UniAssistTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
                    bottomBar = { BottomBar(navController = navController) }) {
                    NavHost(
                        navController = navController,
                        startDestination = SCHEDULE_ROUTE,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(SCHEDULE_ROUTE) {
                            val teacherScheduleViewModel =
                                daggerViewModel(key = "ScheduleViewModel") { teacherComponent.getScheduleViewModel() }
                            TeacherScheduleScreen(teacherScheduleViewModel)
                        }
                        composable(MESSAGES_ROUTE) {
                            Text(text = "Messages")
                        }
                        composable(PROFILE_ROUTE) {
                            Text(text = "Profile")
                        }
                    }
                }
            }
        }
    }
}