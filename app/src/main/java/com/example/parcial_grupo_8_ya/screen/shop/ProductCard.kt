package com.example.parcial_grupo_8_ya.screen.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductCard(
    productName: String,
    productDescription: String,
    productPrice: String,
    productImageRes: Int,
    onAddToCartClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(160.dp)
            .height(240.dp)
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
        ,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = productImageRes),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = productName,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = productDescription,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.Start).weight(1f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = productPrice,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Button(
                    onClick = onAddToCartClick,
                    modifier = Modifier.size(32.dp),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF53B175))
                ) {
                    Text(
                        text = "+",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

