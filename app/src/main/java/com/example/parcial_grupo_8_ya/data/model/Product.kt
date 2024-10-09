package com.example.parcial_grupo_8_ya.data.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val description: String,
    val image: String // Recurso de imagen local (ID de drawable)
)

val supermarketProductList = listOf(
    Product(
        id = "1",
        name = "Milk 1L",
        price = 1.5,
        description = "Fresh cow's milk, rich in calcium and essential nutrients.",
        image = ""
    ),
    Product(
        id = "2",
        name = "Eggs (12 units)",
        price = 2.3,
        description = "12 large organic eggs, perfect for breakfast or baking.",
        image = ""
    ),
    Product(
        id = "3",
        name = "Whole Wheat Bread",
        price = 1.2,
        description = "Healthy whole wheat bread, perfect for sandwiches and toasts.",
        image = ""
    ),
    Product(
        id = "4",
        name = "Orange Juice 1L",
        price = 2.0,
        description = "100% natural orange juice with no added sugar.",
        image = ""
    ),
    Product(
        id = "5",
        name = "Tomatoes (1kg)",
        price = 1.8,
        description = "Fresh organic tomatoes, ideal for salads or cooking.",
        image = ""
    )
)