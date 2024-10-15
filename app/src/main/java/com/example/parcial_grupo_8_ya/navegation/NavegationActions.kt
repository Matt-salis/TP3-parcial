package com.example.parcial_grupo_8_ya.navegation

import LocationScreenPreview
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_8_ya.screen.account.AccountScreenPreview
import com.example.parcial_grupo_8_ya.screen.categories.BeveragesScreen
import com.example.parcial_grupo_8_ya.screen.checkout.MyCart
import com.example.parcial_grupo_8_ya.screen.details.ProductDetails
import com.example.parcial_grupo_8_ya.screen.explore.CategoryScreen
import com.example.parcial_grupo_8_ya.screen.favorites.FavoritesScreen
import com.example.parcial_grupo_8_ya.screen.login.LoginForm
import com.example.parcial_grupo_8_ya.screen.onboarding.Onboarding
import com.example.parcial_grupo_8_ya.screen.orderaccepted.OrderAcceptedScreen
import com.example.parcial_grupo_8_ya.screen.register.RegisterScreen
import com.example.parcial_grupo_8_ya.screen.search.SearchScreen
import com.example.parcial_grupo_8_ya.screen.shop.Shop
import com.example.parcial_grupo_8_ya.screen.splash.SplashScreen
import com.example.parcial_grupo_8_ya.viewModels.SearchViewModel


sealed class DestinationScreen(val route: String) {
    object splashScreenDest : DestinationScreen(route = "splash_screen")
    object onboardingScreen : DestinationScreen(route = "main_screen")
    object loginDest : DestinationScreen(route = "login_screen")
    object signupDest : DestinationScreen(route = "signup_screen")
    object locationDest : DestinationScreen(route = "location_screen")
    object shopDest : DestinationScreen(route = "shop_screen")
    object beveragesDest : DestinationScreen(route = "beverages_screen")
    object productDest : DestinationScreen(route = "product_screen")
    object accountDest : DestinationScreen(route = "account_screen")
    object exploreDest : DestinationScreen(route = "explore_screen")
    object searchDest : DestinationScreen(route = "search_screen")
    object orderAcceptedDest : DestinationScreen(route = "order_accepted_screen")
    object favoriteDest : DestinationScreen(route = "favorite_screen")
    object myCartDest : DestinationScreen(route = "my_cart_screen")
}

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    val searchViewModel: SearchViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = DestinationScreen.splashScreenDest.route
    ) {
        composable(route = DestinationScreen.splashScreenDest.route) {
            SplashScreen(navController = navController)
        }

        composable(route = DestinationScreen.onboardingScreen.route) {
            Onboarding(navController = navController)
        }

        composable(route = DestinationScreen.locationDest.route) {
            LocationScreenPreview(navController = navController)
        }

        composable(route = DestinationScreen.shopDest.route) {
            Shop(navController = navController)
        }

        composable(route = DestinationScreen.loginDest.route) {
            LoginForm(navController = navController)
        }
        composable(route = DestinationScreen.beveragesDest.route) {
            BeveragesScreen(navController = navController)
        }
        composable(route = DestinationScreen.signupDest.route) {
            RegisterScreen(navController = navController)
        }

        composable(route = DestinationScreen.productDest.route) {
            ProductDetails(navController = navController)
        }

        composable(route = DestinationScreen.accountDest.route) {
            AccountScreenPreview(navController = navController)
        }

        composable(route = DestinationScreen.exploreDest.route) {
            CategoryScreen(navController = navController, searchViewModel = searchViewModel)
        }

        composable(route = DestinationScreen.searchDest.route) {
            SearchScreen(navController = navController, searchViewModel = searchViewModel)
        }

        composable(route = DestinationScreen.orderAcceptedDest.route) {
            OrderAcceptedScreen(navController = navController)
        }

        composable(route = DestinationScreen.favoriteDest.route) {
            FavoritesScreen(navController = navController)
        }

        composable(route = DestinationScreen.myCartDest.route) {
            MyCart(navController = navController)
        }
    }
}