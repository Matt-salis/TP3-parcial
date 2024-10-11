package com.example.parcial_grupo_8_ya.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.parcial_grupo_8_ya.R

data class Category(
    val name: String,
    val image: Painter,
    val backgroundColor: Color
)

@Composable
fun getCategories(): List<Category> {
    return listOf(
        Category("Fresh Fruits & Vegetables", painterResource(id = R.drawable.fresh_fruits), Color(0xFFDFF7E0)),
        Category("Cooking Oil & Ghee", painterResource(id = R.drawable.cooking_oil), Color(0xFFFFF3DB)),
        Category("Meat & Fish", painterResource(id = R.drawable.fresh_fruits), Color(0xFFFFE2E5)),
        Category("Bakery & Snacks", painterResource(id = R.drawable.fresh_fruits), Color(0xFFF3E0FF)),
        Category("Dairy & Eggs", painterResource(id = R.drawable.fresh_fruits), Color(0xFFFFF7D7)),
        Category("Beverages", painterResource(id = R.drawable.fresh_fruits), Color(0xFFE0F4FF))
    )
}