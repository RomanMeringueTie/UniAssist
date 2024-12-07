package ru.sibsutis.uniassist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.core.utils.daggerViewModel
import ru.sibsutis.student.data.repository.StudentRepository
import ru.sibsutis.student.data.service.StudentService
import ru.sibsutis.student.di.DaggerStudentComponent
import ru.sibsutis.student.domain.GetStudentScheduleUseCase
import ru.sibsutis.student.presentation.StudentScheduleViewModel
import ru.sibsutis.student.ui.StudentScheduleScreen
import ru.sibsutis.uniassist.di.DaggerAppComponent
import ru.sibsutis.uniassist.navigation.BottomBar
import ru.sibsutis.uniassist.navigation.MESSAGES_ROUTE
import ru.sibsutis.uniassist.navigation.PROFILE_ROUTE
import ru.sibsutis.uniassist.navigation.SCHEDULE_ROUTE
import ru.sibsutis.uniassist.ui.theme.UniAssistTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.builder().build().inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            UniAssistTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBar(navController = navController) }) {
                    NavHost(navController = navController, startDestination = SCHEDULE_ROUTE) {
                        composable(SCHEDULE_ROUTE) {
                            val component = DaggerStudentComponent.builder().build()
                            val viewModel: StudentScheduleViewModel =
                                daggerViewModel { component.getScheduleViewModel() }
                            StudentScheduleScreen(viewModel)
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