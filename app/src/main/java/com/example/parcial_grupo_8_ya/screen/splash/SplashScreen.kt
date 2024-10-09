package com.example.parcial_grupo_8_ya.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.screen.onboarding.Onboarding
import kotlinx.coroutines.delay


sealed class DestinationScreen(val route: String) {
    object SplashScreenDest : DestinationScreen(route = "splash_screen")
    object MainScreenDest : DestinationScreen(route = "main_screen")
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
        startDestination = DestinationScreen.SplashScreenDest.route // Comienza en la splash
    ) {
        composable(route = DestinationScreen.SplashScreenDest.route) {
            SplashScreen(navController = navController) // Pantalla de splash
        }

        composable(route = DestinationScreen.MainScreenDest.route) {
            Onboarding() // Pantalla de onboarding
        }
    }
}




@Composable
fun SplashScreen(navController: NavController) {
    // Usamos LaunchedEffect para navegar automáticamente después de un tiempo
    LaunchedEffect(key1 = true) {
        // Luego de la espera, navegamos a la siguiente pantalla
        navController.navigate(DestinationScreen.MainScreenDest.route) {
            // Evita regresar a la SplashScreen con el botón de retroceso
            popUpTo(DestinationScreen.SplashScreenDest.route) {
                inclusive = true
            }
        }
    }
    // Mostramos la imagen de la splash screen
    DesignSplashScreen(
        imagePainter = painterResource(id = R.drawable.logo_splash),
    )
}
