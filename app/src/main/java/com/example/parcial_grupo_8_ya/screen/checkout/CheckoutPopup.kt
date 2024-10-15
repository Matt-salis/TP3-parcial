package com.example.parcial_grupo_8_ya.screen.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.screen.splash.DestinationScreen
import com.example.parcial_grupo_8_ya.ui.component.CommonButton

@Composable
fun CheckoutPopup(onClose: () -> Unit, navController: NavController) {

    val primaryColor = MaterialTheme.colorScheme.primary
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .background(Color.White)
            .zIndex(2f)
            .padding(16.dp),

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
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
            LazyColumn ( modifier = Modifier
            ) {
                items(itemsList) { item ->
                    ListItem(
                        name = item.name,
                        value = item.value,
                        onIconClick = { /* on Icon Click */ }
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))

            TermsAndConditionsText()

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CommonButton(
                    text = "Place Order",
                    onClick = {navController.navigate(DestinationScreen.orderAcceptedDest.route)}
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, fontSize = 18.sp, color = Color(0xFF7C7C7C))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (value) {
                is Int -> {

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
