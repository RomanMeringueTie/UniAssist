package ru.sibsutis.uniassist.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

data class BottomBarItem(
    @StringRes val titleRes: Int,
    val route: Route,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)