package com.example.parcial_grupo_8_ya.screen.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_8_ya.navegation.DestinationScreen

@Composable
fun FilterPopUp(onDismiss: () -> Unit, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
                    .align(Alignment.TopCenter)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { onDismiss() }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Filters", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (72).dp)
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF2F2F2))
                        .padding(16.dp),
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    FilterSection(
                        title = "Categories",
                        items = listOf("Eggs", "Noodles & Pasta", "Chips & Crisps", "Fast Food")
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FilterSection(
                        title = "Brand",
                        items = listOf("Individual Collection", "Cocola", "Ifad", "Kazi Farmas")
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    Button(
                        onClick = {
                            navController.navigate(DestinationScreen.searchDest.route)
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(364.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(19.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text(text = "Apply Filter", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun FilterSection(title: String, items: List<String>) {
    Column {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        items.forEach { item ->
            var isChecked by remember { mutableStateOf(false) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF4CAF50),
                        uncheckedColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = item, color = if (isChecked) Color(0xFF4CAF50) else Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilterPopUp() {
    var showFilter by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    Scaffold(
    ) { innerPadding ->
        FilterPopUp(onDismiss = { showFilter }, navController)
    }
}