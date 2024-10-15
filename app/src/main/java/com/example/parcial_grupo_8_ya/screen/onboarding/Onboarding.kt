package com.example.parcial_grupo_8_ya.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.screen.splash.DestinationScreen
import com.example.parcial_grupo_8_ya.ui.component.CommonButton

@Composable
fun Onboarding(navController: NavController) {
    OnboardingScreen(navController)
}

@Composable
fun OnboardingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF53B175)) // Color de fondo
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondo_onboarding),
            contentDescription = "Fondo onboarding",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize() // Ocupa todo el espacio
        )

        // Contenido superpuesto
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Padding para no estar pegado a los bordes
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo_zanahoria),
                    contentDescription = "Logotipo Zanahoria",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.width(50.dp)
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Text(
                text = "Welcome",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Text(
                text = "to our store",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Texto pequeño
            Spacer(modifier = Modifier.height(32.dp))
            CommonButton(
                text = "Get Started",
                onClick = {navController.navigate(DestinationScreen.loginDest.route)}
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
