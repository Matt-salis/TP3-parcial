package com.example.parcial_grupo_8_ya.screen.explore

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.data.model.Category
import com.example.parcial_grupo_8_ya.data.model.getCategories
import com.example.parcial_grupo_8_ya.navegation.DestinationScreen
import com.example.parcial_grupo_8_ya.ui.component.BottomNavigationBar
import com.example.parcial_grupo_8_ya.ui.component.CustomTopBar
import com.example.parcial_grupo_8_ya.ui.component.SearchBar
import com.example.parcial_grupo_8_ya.viewModels.SearchViewModel

@Composable
fun Category(navController: NavController, modifier: Modifier = Modifier, searchViewModel: SearchViewModel) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            searchQuery = searchViewModel.searchQuery.value,
            onSearchQueryChange = { newQuery -> searchViewModel.updateSearchQuery(newQuery) },
            navController = navController
        )
        Spacer(modifier = Modifier.height(18.dp))
        CategoryGrid(getCategories(), navController)
    }
}

@Composable
fun CategoryGrid(categories: List<Category>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories.size) { index ->
            CategoryCard(category = categories[index], navController)
        }
    }
}

@Composable
fun CategoryCard(category: Category, navController: NavController) {
    val borderColor = category.backgroundColor.darker()
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = category.backgroundColor),
        border = BorderStroke(1.dp, borderColor),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        onClick = { navController.navigate(DestinationScreen.beveragesDest.route) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = category.image,
                contentDescription = category.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = category.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

fun Color.darker(): Color {
    return this.copy(
        red = this.red * 0.9f,
        green = this.green * 0.9f,
        blue = this.blue * 0.9f
    )
}


@Composable
fun CategoryScreen(navController: NavController, searchViewModel: SearchViewModel) {
    Scaffold(
        topBar = {
            CustomTopBar(title = "Find Products")
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Category(navController, Modifier.padding(innerPadding), searchViewModel = searchViewModel)
    }
}