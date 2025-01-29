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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.sibsutis.uniassist.R

@Composable
internal fun BottomBar(navController: NavController) {
    val itemsList = remember { BottomBarItem.entries }
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        itemsList.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                },
                icon = {
                    BadgedBox(badge = { if (item.badgeAmount != null) Badge { Text(text = item.badgeAmount.toString()) } }) {
                        Icon(
                            imageVector = if (selectedItem == index) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            },
                            contentDescription = stringResource(id = item.titleRes)
                        )
                    }
                },
                label = { Text(text = stringResource(id = item.titleRes), fontSize = 12.sp) }
            )
        }
    }
}