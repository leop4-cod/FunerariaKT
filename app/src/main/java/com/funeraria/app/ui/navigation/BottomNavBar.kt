package com.funeraria.app.ui.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.funeraria.app.navigation.Screen
import com.funeraria.app.ui.theme.*

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector,
    val iconSelected: ImageVector
)

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(Screen.Home,         "Inicio",     Icons.Outlined.Dashboard,   Icons.Filled.Dashboard),
        BottomNavItem(Screen.ClienteList,  "Clientes",   Icons.Outlined.People,      Icons.Filled.People),
        BottomNavItem(Screen.ProductoList, "Productos",  Icons.Outlined.Inventory2,  Icons.Filled.Inventory2),
        BottomNavItem(Screen.ContratoList, "Contratos",  Icons.Outlined.Description, Icons.Filled.Description),
        BottomNavItem(Screen.Profile,      "Perfil",     Icons.Outlined.AccountCircle, Icons.Filled.AccountCircle),
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface(
        color = SurfaceColor,
        shadowElevation = 12.dp,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        NavigationBar(
            containerColor = SurfaceColor,
            tonalElevation = 0.dp,
            modifier = Modifier.navigationBarsPadding().widthIn(max = 600.dp).height(68.dp)
        ) {
            items.forEach { item ->
                val isSelected = currentRoute?.startsWith(item.screen.route.substringBefore("/{")) == true ||
                        currentRoute?.startsWith(item.screen.route.substringBefore("/form")) == true ||
                        currentRoute == item.screen.route
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (currentRoute != item.screen.route) {
                            navController.navigate(item.screen.route) {
                                popUpTo(Screen.Home.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.iconSelected else item.icon,
                            contentDescription = item.label,
                        )
                    },
                    label = {
                        Text(
                            item.label,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor    = PrimaryPurple,
                        selectedTextColor    = PrimaryPurple,
                        indicatorColor      = SoftPurple,
                        unselectedIconColor  = TextTertiary,
                        unselectedTextColor  = TextTertiary,
                    )
                )
            }
        }
    }
}
