package com.example.parcial_grupo_8_ya.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.data.model.Product
import com.example.parcial_grupo_8_ya.screen.shop.ProductCard
import com.example.parcial_grupo_8_ya.ui.component.BottomNavigationBar
import com.example.parcial_grupo_8_ya.ui.component.CustomTopBar
import com.example.parcial_grupo_8_ya.ui.component.SearchBar
import com.example.parcial_grupo_8_ya.viewModels.SearchViewModel

@Composable
fun SearchScreen(navController: NavController, searchViewModel: SearchViewModel) {
    val searchQuery by searchViewModel.searchQuery

    Scaffold(
        topBar = {
            CustomTopBar("Search")
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SearchBar(searchQuery, { newQuery -> searchViewModel.updateSearchQuery(newQuery) }, navController, modifier = Modifier.padding(16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            ProductGrid(products = sampleProducts())
        }
    }
}

@Composable
fun ProductGrid(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(products) { product ->
            ProductCard(product.name, product.description,"$ ${product.price}", product.image, {})
        }
    }
}


fun sampleProducts(): List<Product> {
    return listOf(
        Product("1", "Egg Chicken Red", 1.99, "4pcs, Price", R.drawable.egg_red),
        Product("2", "Egg Chicken White", 1.50, "180g, Price", R.drawable.egg_white),
        Product("3", "Egg Pasta", 15.99, "30gm, Price", R.drawable.egg_pasta),
        Product("4", "Egg Noodles", 15.99, "2L, Price", R.drawable.egg_noodles),
        Product("5", "Eggless Mayonnaise", 10.29, "500g, Price", R.drawable.eggless_mayonnaise),
        Product("6", "Egg Noodles Fideos", 1.99, "2L, Price", R.drawable.egg_noodles_fideos)
    )
}