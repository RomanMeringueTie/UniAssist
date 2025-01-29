package ru.sibsutis.uniassist.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ru.sibsutis.uniassist.R

enum class BottomBarItem(
    @StringRes val titleRes: Int,
    val route: Route,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
) {
    Schedule(
        titleRes = R.string.schedule,
        route = Route.ScheduleRoute,
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange
    ),
    Messages(
        titleRes = R.string.messages,
        route = Route.MessageRoute,
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email
    ),
    Profile(
        titleRes = R.string.profile,
        route = Route.ProfileRoute,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )
}