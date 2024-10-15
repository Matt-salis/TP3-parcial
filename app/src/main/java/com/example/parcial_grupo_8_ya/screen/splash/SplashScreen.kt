package com.example.parcial_grupo_8_ya.screen.splash

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
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.navegation.DestinationScreen


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
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        navController.navigate(DestinationScreen.onboardingScreen.route) {
            popUpTo(DestinationScreen.splashScreenDest.route) {
                inclusive = true
            }
        }
    }
    DesignSplashScreen(
        imagePainter = painterResource(id = R.drawable.logo_splash),
    )
}
