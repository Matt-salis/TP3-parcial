package com.example.parcial_grupo_8_ya.screen.details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.DefaultStrokeLineCap
import androidx.compose.ui.graphics.vector.DefaultStrokeLineJoin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.navegation.DestinationScreen

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ProductDetails(navController: NavController) {
    Surface {
        Column {
            ProductDetailBar(navController)
            ProductInfo(navController)
        }
    }
}

@Composable
fun ProductInfo(navController: NavController) {
    val firstTitle = "Product Detail"
    val secondTitle = "Nutritions"
    val thirdTitle = "Review"
    val appleDesc =
        "Apples are nutritious. Apples may be good for weight loss. Apples may be good for your heart. As part of a healthy and varied diet."

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp),
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Column {

                ProductImage()
                Spacer(modifier = Modifier.height(15.dp))

                ProductDetailsText()
                Spacer(modifier = Modifier.height(1.dp))

                PriceTextField(price = "1kg, Price")


                ItemQuantity()

                Spacer(modifier = Modifier.height(24.dp))

                ExpandableBox(firstTitle, appleDesc)
                Spacer(modifier = Modifier.height(24.dp))
                ExpandableBox(secondTitle, "")
                Spacer(modifier = Modifier.height(24.dp))
                ExpandableBox(thirdTitle, "")
                Spacer(modifier = Modifier.height(24.dp))

                AddToBasketButton(navController)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ProductImage() {
    Box(
        modifier = Modifier
            .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.apple_hero_image),
            contentDescription = "Product Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun ProductDetailsText() {
    Text(
        text = "Naturel Red Apple",
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        textAlign = TextAlign.Start
    )
}

@Composable
fun PriceTextField(price: String) {
    Text(
        text = price,
        fontSize = 19.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Gray,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AddToBasketButton(navController: NavController) {
    Button(
        onClick = { navController.navigate(DestinationScreen.shopDest.route) },
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF53B175)),
        contentPadding = PaddingValues(),
        modifier = Modifier
            .size(width = 350.dp, height = 60.dp)
            .padding(start = 25.dp)
    ) {
        Text("Add To Basket", fontSize = 16.sp)
    }
}

@Composable
fun ExpandableBox(title: String, description: String) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.clickable { expanded = !expanded }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                fontSize = 18.sp
            )

            Icon(
                imageVector = (if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore),
                contentDescription = "Expand Arrow"
            )
        }
        if (expanded) {
            Text(
                text = description,
                modifier = Modifier
                    .padding(start = 16.dp, top = 10.dp)
                    .scale(0.9f)
                    .widthIn(max = 300.dp),
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Product Detail",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 70.dp, top = 10.dp),
                textAlign = TextAlign.Left
            )
        },
        navigationIcon = {
            IconButton(onClick = {navController.popBackStack() }) {
                Icon(
                    Icons.Filled.ArrowBackIosNew,
                    contentDescription = "ArrowBack",
                    tint = Color.Black,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = if (!isSystemInDarkTheme()) Color.White else Color.Gray,
            titleContentColor = Color.Black,
        ),
        modifier = Modifier.height(45.dp)
    )
}

@Composable
fun ItemQuantity() {
    var itemCount by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(
                onClick = { if (itemCount > 0) itemCount-- },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.size(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Gray
                ),
            ) {
                Text("-", fontSize = 30.sp)
            }


            Text(
                text = itemCount.toString(),
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp),

                )

            TextButton(
                onClick = { itemCount++ },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.size(48.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF53B175),
                    containerColor = Color.Transparent
                )
            ) {
                Text("+", fontSize = 30.sp)
            }
        }


        Text(
            text = "$4.99",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}