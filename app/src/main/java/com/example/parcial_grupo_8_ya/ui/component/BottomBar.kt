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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_8_ya.screen.explore.CategoryScreen
import com.example.parcial_grupo_8_ya.ui.theme.Green
import com.example.parcial_grupo_8_ya.ui.theme.Purple40


sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Shop : BottomNavItem("shop", Icons.Filled.Storefront, "Shop")
    object Explore : BottomNavItem("explore", Icons.Filled.Search, "Explore")
    object Cart : BottomNavItem("cart", Icons.Filled.ShoppingCart, "Cart")
    object Favourite : BottomNavItem("favourite", Icons.Filled.Favorite, "Favourite")
    object Account : BottomNavItem("account", Icons.Filled.AccountBox, "Account")
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
            containerColor = Color.Transparent
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            BottomNavItems.forEach { item ->
                val isSelected = currentRoute == item.route

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
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
                        selectedIconColor = Green,  // Verde para el ícono seleccionado
                        unselectedIconColor = Color.Black,  // Negro para los no seleccionados
                        selectedTextColor = Green,  // Verde para el texto seleccionado
                        unselectedTextColor = Color.Black  // Negro para los no seleccionados
                    )
                )
            }
        }
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BottomNavItem.Shop.route) {
        composable(BottomNavItem.Shop.route) { /* Home Screen UI */ }
        composable(BottomNavItem.Explore.route) { CategoryScreen() }
        composable(BottomNavItem.Cart.route) { /* Profile Screen UI */ }
        composable(BottomNavItem.Favourite.route) { /* Profile Screen UI */ }
        composable(BottomNavItem.Account.route) { /* Profile Screen UI */ }
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
        NavigationHost()
    }
}