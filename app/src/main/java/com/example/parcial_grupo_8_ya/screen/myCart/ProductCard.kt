package com.example.parcial_grupo_8_ya.screen.myCart
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource


@Composable
fun ProductCard(
    imageRes: Int,
    productName: String, 
    productDescription: String,
    productPrice: String, 
    quantity: Int, 
    onIncrement: () -> Unit, 
    onDecrement: () -> Unit,
    onClose: () -> Unit 
) {
    Spacer(modifier = Modifier.height(8.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
                    .padding(top = 32.dp)
                ,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = productName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color(0xFF181725)
                        )
                        Text(
                            text = productDescription,
                            fontSize = 14.sp,
                            color = Color(0xFF7C7C7C)
                        )
                    }

                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color(0xFF7C7C7C)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onDecrement) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Decrease quantity",
                                tint = Color(0xFF181725),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Text(
                            text = quantity.toString(),
                            fontSize = 18.sp,
                            color = Color(0xFF181725),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        IconButton(onClick = onIncrement) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Increase quantity",
                                tint = Color(0xFF00C569),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Text(
                        text = productPrice,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF181725)
                    )
                }
            }
        }

        HorizontalDivider(color = Color.LightGray,
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp)
    }
}
