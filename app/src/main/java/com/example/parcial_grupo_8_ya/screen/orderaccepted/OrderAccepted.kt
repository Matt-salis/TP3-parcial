package com.example.parcial_grupo_8_ya.screen.orderaccepted

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.navegation.DestinationScreen
import com.example.parcial_grupo_8_ya.screen.login.GradientBackground
import com.example.parcial_grupo_8_ya.ui.component.CommonButton

@Composable
fun OrderAccepted(navController: NavController) {
    OrderAcceptedScreen(navController)
}

@Composable

fun OrderAcceptedScreen(navController: NavController) {
    FadeInTransition()

    Surface{
        GradientBackground()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(100.dp))


            CheckMarkImage()

            Spacer(modifier = Modifier.height(50.dp))


            Text(
                text = "Your Order has been accepted",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Your items have been placed and is on it's way to being processed",
                style = TextStyle(
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
            )


            Spacer(modifier = Modifier.height(110.dp))
            CommonButton(
                text = "Track Order",
                onClick = {navController.navigate(DestinationScreen.orderAcceptedDest.route)}
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate(DestinationScreen.shopDest.route) },
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .size(width = 350.dp, height = 30.dp)
                    .padding(start = 8.dp)
            ) {
                Text("Back to home", color = Color.Black)
            }
        }
    }
}

@Composable
fun FadeInTransition() {
    var visible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        Box(
            Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            Box(
                Modifier
                    .align(Alignment.Center)
                    .animateEnterExit(

                        enter = slideInVertically(),
                        exit = slideOutVertically()
                    )
                    .sizeIn(minWidth = 256.dp, minHeight = 64.dp)
                    .background(Color.Red)
            ) {

                CheckMarkImage()
            }
        }
    }

}

@Composable
fun CheckMarkImage() {
    Image(
        painter = painterResource(id = R.drawable.order_accepted),
        contentDescription = "Order Accepted",
        modifier = Modifier.fillMaxWidth().padding(start = 40.dp, end = 60.dp),
        alignment = Alignment.Center
    )
}
