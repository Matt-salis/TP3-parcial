package com.example.parcial_grupo_8_ya.screen.checkout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.data.model.Product
import com.example.parcial_grupo_8_ya.screen.myCart.ProductCard
import com.example.parcial_grupo_8_ya.screen.myCart.sampleProducts
import com.example.parcial_grupo_8_ya.screen.shop.ShopScreen
import com.example.parcial_grupo_8_ya.ui.component.BottomNavigationBar
import com.example.parcial_grupo_8_ya.ui.component.CustomTopBar


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
fun Checkout() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CustomTopBar(title = "Shop")
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        CheckoutScreen(Modifier.padding(innerPadding))
    }

}


@Composable
fun CheckoutScreen(mod: Modifier, viewModel: CheckoutViewModel = viewModel()) {
    // Estado de visibilidad del checkout
    val isCheckoutVisible by viewModel.isCheckoutVisible.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Lista de productos
        LazyColumn(
            modifier = mod
                //.weight(1f) // Hace que la lista sea scrolleable y ocupe el espacio disponible TODO: check
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
                    onIncrement = { /* onincrement */ },
                    onDecrement = { /* onDecrement */ },
                    onClose = { /* onClose */ }
                )
            }
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
                    CheckoutPopup(onClose = { viewModel.hideCheckout() })
                }
            }
        }

        Button(
            onClick = { viewModel.showCheckout() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .zIndex(1f)
        ) {
            Text("Go To Checkout")
        }
    }
}

@Composable
fun CheckoutPopup(onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .background(Color.White)
            .zIndex(2f)
            .padding(16.dp),
        //contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = "Checkout",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                IconButton(
                    onClick = { onClose() },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close",
                        tint = Color(0xFF181725),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.fillMaxWidth())
            LazyColumn {
                items(itemsList) { item ->
                    ListItem(
                        name = item.name,
                        value = item.value,
                        onIconClick = { /* on Icon Click */ }
                    )
                }
            }

            TermsAndConditionsText()

            Button(
                onClick = { /* place order click */ },
                modifier = Modifier.fillMaxWidth()
                                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF53B175))
            ) {
                Text(
                    text = "Place Order",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

data class ListItemData(
    val name: String,
    val value: Any
)

@Composable
fun ListItem(name: String, value: Any, onIconClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, fontSize = 18.sp, color = Color(0xFF7C7C7C))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (value) {
                is Int -> {
                    // Si es un icono, mostrar la imagen
                    Image(
                        painter = painterResource(id = value),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                is String -> {
                    Text(
                        text = value,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Keyboard Arrow Right",
                tint = Color(0xFF181725),
                modifier = Modifier.size(24.dp)
            )
        }
    }
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.fillMaxWidth()) 
}
val itemsList = listOf(
    ListItemData(name = "Delivery", value = "Select Method"),
    ListItemData(name = "Payment", value = R.drawable.payment),
    ListItemData(name = "Promo Code", value = "Pick Discount"),
    ListItemData(name = "Total Cost", value = "$13.97")
)


@Composable
fun TermsAndConditionsText() {
    val normalText = "By placing an order you agree to our\n"

    val annotatedString = buildAnnotatedString {
        append(normalText)

        withStyle(style = SpanStyle(color = Color(0xFF181725))) {
            append("Terms")
        }
        append(" and ")
        withStyle(style = SpanStyle(color = Color(0xFF181725))) {
            append("Conditions")
        }
    }

    Text(
        text = annotatedString,
        style = TextStyle(color = Color.Gray),
        fontWeight = FontWeight.Bold

    )
}
