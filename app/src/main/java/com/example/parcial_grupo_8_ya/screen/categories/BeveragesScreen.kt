package com.example.parcial_grupo_8_ya.screen.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.data.model.Product
import com.example.parcial_grupo_8_ya.screen.shop.ProductCard
import com.example.parcial_grupo_8_ya.ui.theme.Purple40

val beverages = listOf(
    Product("4", "Diet Coke", 1.99, "325ml, Price", R.drawable.diet_coke),
    Product("5", "Sprite Can", 1.50, "325ml, Price", R.drawable.sprite),
    Product("6", "Apple & Grape Juice", 15.99, "2L, Price", R.drawable.apple_grape_juice),
    Product("7", "Orange Juice", 15.99, "2L, Price", R.drawable.orange_juice),
    Product("8", "Coca Cola Can", 4.99, "325ml, Price", R.drawable.coca_cola),
    Product("9", "Pepsi Can", 4.99, "330ml", R.drawable.pepsi)
)

@Preview
@Composable
fun BeveragesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        BeverageHeading()
        BeveragesCardList(beverages)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeverageHeading() {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        end = 27.dp
                    ), // le agrego mas padding para que este alineado con grid
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Beverages",
                    fontSize = 20.sp,
                    color = Color.Black,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Arrow Back",
                    tint = Color.Black,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = if (!isSystemInDarkTheme()) Color.White else Purple40,
            titleContentColor = Color.Black,
        ),
        modifier = Modifier.height(56.dp)
    )

}


@Composable
fun BeveragesCardList(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        items(products) { product ->
            ProductCard(
                productName = product.name,
                productDescription = product.description,
                productPrice = product.price.toString(),
                productImageRes = product.image
            ) {

            }

        }

    }
}

