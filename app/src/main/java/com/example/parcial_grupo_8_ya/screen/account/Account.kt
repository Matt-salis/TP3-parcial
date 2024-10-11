package com.example.parcial_grupo_8_ya.screen.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.parcial_grupo_8_ya.R


@Composable
fun AccountScreen(onMenuClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White) ) {
        // Top bar with menu icon and Account title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { onMenuClick() }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }

            Text(
                text = "Account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(48.dp)) // Empty space to balance the layout
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Profile Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circular Profile Picture
            Image(
                painter = painterResource(id = R.drawable.profilepic), // Replace with your profile picture resource
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Profile Name and Details
            Column {
                Text(
                    text = "Afsar Hossen", // Bold text
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "imshuvo97@gmail.com", // Thin text
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Option items section
        val options = listOf(
            "Orders" to R.drawable.orders_icon,
            "My Details" to R.drawable.my_details_icon,
            "Delivery Address" to R.drawable.delicery_address,
            "Payment Methods" to R.drawable.vector_icon,
            "Promo Code" to R.drawable.promo_cord_icon,
            "Notifications" to R.drawable.bell_icon,
            "Help" to R.drawable.help_icon,
        )

        options.forEach { (title, icon) ->
            AccountOption(title = title, icon = icon) { /* Handle click */ }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Dark Mode", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            
            Spacer(modifier = Modifier.weight(1f))  // Push the switch to the end

            Switch(
                checked = false,
                onCheckedChange = {},
                colors = SwitchDefaults.colors(checkedThumbColor = Color.Black)
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2F3F2)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(60.dp),
                shape = RoundedCornerShape(30),
            ) {

                Image(painter = painterResource(id = R.drawable.exit), contentDescription = "exit icon" )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Log Out", fontSize = 18.sp, color = Color(0xFF53B175))
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun AccountOption(title: String, icon: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.4.dp, Color.Black)
            .background(Color.White)
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon), // Replace with your profile picture resource
            contentDescription = "Profile Picture",
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.back_arrow), // Replace with your profile picture resource
            contentDescription = "Profile Picture",
            modifier = Modifier.size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    AccountScreen(onMenuClick = {})
}
