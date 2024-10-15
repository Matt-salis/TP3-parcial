package com.example.parcial_grupo_8_ya.screen.checkout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.screen.myCart.ProductCard
import com.example.parcial_grupo_8_ya.ui.component.BottomNavigationBar
import com.example.parcial_grupo_8_ya.ui.component.CommonButton
import com.example.parcial_grupo_8_ya.ui.component.CustomTopBar
import com.example.parcial_grupo_8_ya.viewModels.CheckoutViewModel

data class Product(
    val imageRes: Int,            
    val productName: String,      
    val productDescription: String,
    val productPrice: String,     
    val quantity: Int              
)

val prods = listOf(
    Product(
        imageRes = R.drawable.morrones,
        productName = "Bell Pepper Red",
        productDescription = "1kg, Price",
        productPrice = "$4.99",
        quantity = 1
    ),
    Product(
        imageRes = R.drawable.eggs,
        productName = "Egg Chicken REd",
        productDescription = "4pcs, Price",
        productPrice = "$1.99",
        quantity = 2
    ),
    Product(
        imageRes = R.drawable.bananas,
        productName = "Organic Bananas",
        productDescription = "12kg, Price",
        productPrice = "$3.00",
        quantity = 3
    ),
    Product(
        imageRes = R.drawable.jengibre_shop,
        productName = "Ginger",
        productDescription = "250gm, Price",
        productPrice = "$2.99",
        quantity = 1
    ),
    Product(
        imageRes = R.drawable.apple,
        productName = "Red Apple",
        productDescription = "500g, Price",
        productPrice = "$6.00",
        quantity = 2
    ),
    Product(
        imageRes = R.drawable.bananas,
        productName = "Bananas",
        productDescription = "500g, Price",
        productPrice = "$6.00",
        quantity = 2
    )
)

@Composable
fun MyCart(navController: NavController) {
    MyCartScreen(navController)
}


@Composable
fun MyCartScreen(navController: NavController) {
    val viewModel: CheckoutViewModel = viewModel()
    val isCheckoutVisible by viewModel.isCheckoutVisible.collectAsState()


    Scaffold(
        topBar = {
            CustomTopBar(title = "Shop")
        },
        bottomBar = {
            if (!isCheckoutVisible) { 
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        MyCartScreen(Modifier.padding(innerPadding), navController = navController)
    }

}

@Composable
fun MyCartScreen(mod: Modifier, viewModel: CheckoutViewModel = viewModel(), navController: NavController) {
    // Estado de visibilidad del checkout
    val isCheckoutVisible by viewModel.isCheckoutVisible.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = mod
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Lista de productos
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .heightIn(max = 400.dp)
                    .fillMaxWidth()
            ) {
                items(prods.size) { index ->
                    val product = prods[index]
                    ProductCard(
                        imageRes = product.imageRes,
                        productName = product.productName,
                        productDescription = product.productDescription,
                        productPrice = product.productPrice,
                        quantity = product.quantity,
                        onIncrement = { /* onIncrement */ },
                        onDecrement = { /* onDecrement */ },
                        onClose = { /* onClose */ }
                    )
                }
            }
            CommonButton(
                text = "Go To Checkout",
                onClick = {viewModel.showCheckout()}
            )
        }

        if (isCheckoutVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { viewModel.hideCheckout() } // Cerrar checkout
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f)
                .padding(bottom = 0.dp), 
            contentAlignment = Alignment.BottomCenter
        ) {
            AnimatedVisibility(
                visible = isCheckoutVisible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(Color.Red)
                        .zIndex(2f)
                ) {
                    CheckoutPopup(onClose = { viewModel.hideCheckout() }, navController = navController)
                }
            }
        }
    }
}

