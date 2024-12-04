package ru.sibsutis.uniassist.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

const val SCHEDULE_ROUTE = "SCHEDULE"
const val MESSAGES_ROUTE = "MESSAGES"
const val PROFILE_ROUTE = "PROFILE"

@Composable
fun BottomBar(navController: NavController) {
    val itemsList = listOf(
        BottomBarItem(
            title = "Schedule",
            route = SCHEDULE_ROUTE,
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange
        ),
        BottomBarItem(
            title = "Messages",
            route = MESSAGES_ROUTE,
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email
        ),
        BottomBarItem(
            title = "Profile",
            route = PROFILE_ROUTE,
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        )
    )
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        itemsList.mapIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.title)
                },
                icon = {
                    BadgedBox(badge = { Badge() }) {
                        Icon(
                            imageVector = if (selectedItem == index) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            },
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }

}