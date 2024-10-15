package com.example.parcial_grupo_8_ya.screen.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.data.model.Product
import com.example.parcial_grupo_8_ya.screen.splash.DestinationScreen
import com.example.parcial_grupo_8_ya.ui.component.BottomNavigationBar
import com.example.parcial_grupo_8_ya.ui.component.CommonButton

@Composable
fun Favorites(
    favorites: List<Product>, navController: NavController,
    onAddToCartClick: () -> Unit, modifier: Modifier = Modifier
) {
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(favorites) { product ->
                FavoriteItem(product = product, navController)
                HorizontalDivider(thickness = 0.2.dp, color = Color.Gray)
            }
        }
        CommonButton(
            text = "Add All To Cart",
            onClick = { showError = true}
        )

        if (showError) {
            com.example.parcial_grupo_8_ya.screen.error.ErrorPopup(
                onRetry = { showError = false },
                onBackToHome = { navController.navigate(DestinationScreen.shopDest.route) },
                showDialog = showError,
                onDismiss = { showError = false })
        }
    }
}

@Composable
fun FavoriteItem(product: Product, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 26.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = product.name,
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = product.name, fontWeight = FontWeight.Bold)
            Text(text = "${product.description}, Price", color = Color.Gray)
        }

        Text(text = "$${product.price}", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = { navController.navigate(DestinationScreen.productDest.route) }) {
            Icon(Icons.Default.ArrowForward, contentDescription = "More Details")
        }
    }
}

fun sampleFavorites(): List<Product> {
    return listOf(
        Product("1", "Sprite Can", 1.50, "325ml", R.drawable.sprite),
        Product("2", "Diet Coke", 1.99, "355ml", R.drawable.diet_coke),
        Product("3", "Apple & Grape Juice", 15.50, "2L", R.drawable.apple_grape_juice),
        Product("4", "Coca Cola Can", 4.99, "325ml", R.drawable.coca_cola),
        Product("5", "Pepsi Can", 4.99, "330ml", R.drawable.pepsi)
    )
}


@Composable
fun FavoritesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            com.example.parcial_grupo_8_ya.ui.component.CustomTopBar(title = "Favorites")
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        Favorites(
            sampleFavorites(), navController, {},
            Modifier.padding(innerPadding)
        )
    }
}