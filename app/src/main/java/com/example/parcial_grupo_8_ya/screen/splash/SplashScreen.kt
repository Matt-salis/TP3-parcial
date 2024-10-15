package com.example.parcial_grupo_8_ya.screen.splash

import LocationScreenPreview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_8_ya.R
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
fun DesignSplashScreen(
    modifier: Modifier = Modifier,
    imagePainter: Painter,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF53B175)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = imagePainter,
                contentDescription = "Logotipo Splash Screen",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            )
        }
    }
}

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DestinationScreen.splashScreenDest.route // Comienza en la splash
    ) {
        composable(route = DestinationScreen.splashScreenDest.route) {
            SplashScreen(navController = navController) // Pantalla de splash
        }

        composable(route = DestinationScreen.onboardingScreen.route) {
            Onboarding(navController = navController) // Pantalla de onboarding
        }

        composable(route = DestinationScreen.locationDest.route) {
            LocationScreenPreview(navController = navController)
        }

        composable(route = DestinationScreen.shopDest.route) {
            Shop(navController = navController) // Pantalla de Shop
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
            ProductDetails()
        }

        composable(route = DestinationScreen.accountDest.route) {
            AccountScreenPreview(navController = navController)
        }

        composable(route = DestinationScreen.exploreDest.route) {
            CategoryScreen(navController = navController)
        }

        composable(route = DestinationScreen.searchDest.route) {
            SearchScreen(navController = navController)
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


@Composable
fun SplashScreen(navController: NavController) {
    // Usamos LaunchedEffect para navegar automáticamente después de un tiempo
    LaunchedEffect(key1 = true) {
        // Luego de la espera, navegamos a la siguiente pantalla
        navController.navigate(DestinationScreen.onboardingScreen.route) {
            // Evita regresar a la SplashScreen con el botón de retroceso
            popUpTo(DestinationScreen.splashScreenDest.route) {
                inclusive = true
            }
        }
    }
    // Mostramos la imagen de la splash screen
    DesignSplashScreen(
        imagePainter = painterResource(id = R.drawable.logo_splash),
    )
}
