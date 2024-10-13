package com.example.parcial_grupo_8_ya.screen.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_8_ya.R
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_8_ya.data.model.Product
import com.example.parcial_grupo_8_ya.ui.component.BottomNavigationBar
import com.example.parcial_grupo_8_ya.ui.component.CustomTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Shop() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CustomTopBar(title = "Shop")
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        ShopScreen(Modifier.padding(innerPadding))
    }

}

@Composable
fun ShopScreen(padding: Modifier) {

    val exclusiveOfferProducts = listOf(
        Product("1", "Organic Bananas", 4.99, "7pcs, Price", R.drawable.bananas),
        Product("2","Red Apple", 4.99,"1kg, Price", R.drawable.apple),
        Product("3","Organic Bananas", 3.99, "1kg, Price", R.drawable.bananas)
    )
    val bestSellingProducts = listOf(
        Product("1", "Bell Pepper Red", 4.99, "7pcs, Price", R.drawable.morrones),
        Product("2","Ginger", 4.99,"1kg, Price", R.drawable.jengibre_shop),
        Product("3","Bell Pepper Red", 3.99, "1kg, Price", R.drawable.morrones)
    )

    LazyColumn(
        modifier = padding
            .fillMaxSize()
            .background(White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Dhaka, Banassre",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4C4F4D),
                )
                Spacer(modifier = Modifier.height(24.dp))
                Image(
                    painter = painterResource(id = R.drawable.banner),
                    contentDescription = "Banner Shop",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            ProductSection(title = "Exclusive Offer")
            ProductCardList(products = exclusiveOfferProducts)

            ProductSection(title = "Best Selling")
            ProductCardList(products = bestSellingProducts)
        }
    }
}

@Composable
fun ProductSection(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        Text(
            text = "See all",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF53B175),
            modifier = Modifier
                .clickable { /* Action para ver todo */ }
        )
    }
}

@Composable
fun ProductCardList(products: List<Product>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp) // Espacio entre las tarjetas
    ) {

        items(products) { product ->
            ProductCard(
                productName = product.name,
                productDescription = product.description,
                productPrice =  "$${product.price}",
                productImageRes = product.image,
                onAddToCartClick = { /* Manejar la acción de agregar al carrito */ }
            )
        }
    }
}
