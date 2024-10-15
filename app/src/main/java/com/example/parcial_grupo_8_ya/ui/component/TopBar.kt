package com.example.parcial_grupo_8_ya.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_8_ya.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
) {
    var menuExpanded by remember { mutableStateOf(true) }

    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = Color.Black,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { menuExpanded = !menuExpanded }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = if( !isSystemInDarkTheme() ) Color.White else Purple40,
            titleContentColor = Color.Black,
        ),
        modifier = Modifier.height(56.dp)
    )
}

@Preview("TopBar", showBackground = true)
@Preview("TopBar (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewTopBar() {
    CustomTopBar("Shop List")
}