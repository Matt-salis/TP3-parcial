package com.example.parcial_grupo_8_ya.ui.component

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_8_ya.navegation.DestinationScreen
import com.example.parcial_grupo_8_ya.ui.theme.Green
import com.example.parcial_grupo_8_ya.ui.theme.Purple40


sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Shop : BottomNavItem(DestinationScreen.shopDest.route, Icons.Filled.Storefront, "Shop")
    object Explore : BottomNavItem(DestinationScreen.exploreDest.route, Icons.Filled.Search, "Explore")
    object Cart : BottomNavItem(DestinationScreen.myCartDest.route, Icons.Filled.ShoppingCart, "Cart")
    object Favourite : BottomNavItem(DestinationScreen.favoriteDest.route, Icons.Filled.Favorite, "Favourite")
    object Account : BottomNavItem(DestinationScreen.accountDest.route, Icons.Filled.AccountBox, "Account")

}

val BottomNavItems = listOf(
    BottomNavItem.Shop,
    BottomNavItem.Explore,
    BottomNavItem.Cart,
    BottomNavItem.Favourite,
    BottomNavItem.Account,

)

@Composable
fun BottomNavigationBar(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStartPercent = 20, topEndPercent = 20),
        color =if( !isSystemInDarkTheme() ) Color.White else Purple40,
        tonalElevation = 4.dp
    ) {
        NavigationBar(
            containerColor = if( !isSystemInDarkTheme() ) Color.White else Purple40,

        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            BottomNavItems.forEach { item ->
                val isSelected = currentRoute == item.route ||
                        (item.route == DestinationScreen.exploreDest.route && currentRoute == DestinationScreen.searchDest.route)

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id/*startDestinationId*/) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            tint = if (isSelected) Green else Color.Black
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            color = if (isSelected) Green else Color.Black
                        )
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Green,
                        unselectedIconColor = Color.Black,
                        selectedTextColor = Green,
                        unselectedTextColor = Color.Black
                    )
                )
            }
        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview("BottomBar",showBackground = true)
@Preview("BottomBar (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationPreview() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
    }
}