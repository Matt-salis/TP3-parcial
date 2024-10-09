package com.example.parcial_grupo_8_ya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_8_ya.screen.splash.NavigationScreen
import com.example.parcial_grupo_8_ya.screen.splash.SplashScreen
import com.example.parcial_grupo_8_ya.ui.theme.Parcial_grupo_8_YATheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial_grupo_8_YATheme {
                Surface(color = Color(0xFF53B175)) {
                    NavigationScreen() // Asegúrate de que esta función esté bien invocada
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Parcial_grupo_8_YATheme {
        val navController = rememberNavController()
        SplashScreen(navController = navController)
    }
}