import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.screen.splash.DestinationScreen
import com.example.parcial_grupo_8_ya.ui.component.CommonButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(navController : NavController) {
    var selectedOption by remember { mutableStateOf("") }
    var inputText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Back Arrow Icon
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))

            // Image (replace with your image resource)
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Image",
                modifier = Modifier.height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Large Text
            Text("Select your location", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(8.dp))

            // Small Text
            Text("Swithch on your location to stay in tune with", fontSize = 16.sp)
            Text("what’s happening in your area", fontSize = 16.sp)

            Spacer(modifier = Modifier.weight(1f))
            // Dropdown Menu using ExposedDropdownMenuBox
            Text("Your zone", fontSize = 18.sp, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            DropdownMenuWithExposedBox(
                options = listOf("Zone 1", "Zone 2", "Zone 3", "Zone 4"),
                selectedOption = selectedOption,
                onOptionSelected = { selectedOption = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Text Input Field with Label
            Text("Your area", fontSize = 18.sp, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text(text = "Type in your area") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Button
            CommonButton(
               text = "Submit",
               onClick = {navController.navigate(DestinationScreen.shopDest.route)}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuWithExposedBox(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        // TextField to expose the dropdown menu
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text("Choose your zone") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        // Dropdown Menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun LocationScreenPreview(navController: NavController) {
    LocationScreen(navController)
}
