package com.example.parcial_grupo_8_ya.screen.register

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.api.RetrofitClient
import com.example.parcial_grupo_8_ya.data.model.User.UsersListItem
import com.example.parcial_grupo_8_ya.screen.login.GradientBackground
import com.example.parcial_grupo_8_ya.screen.splash.DestinationScreen
import com.example.parcial_grupo_8_ya.viewModels.LoginViewModel
import com.example.parcial_grupo_8_ya.viewModels.RegisterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
                   navController: NavController
) {
    val viewModel = RegisterViewModel()
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Background Gradient
    GradientBackground()

    // Main Column Layout
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Logo
        Logo()

        Spacer(modifier = Modifier.height(40.dp))

        // Title
        SignUpTitle()

        Spacer(modifier = Modifier.height(15.dp))

        // Subtitle
        SignUpSubtitle()

        Spacer(modifier = Modifier.height(40.dp))

        // Input Fields
        InputField("Username", username) { username = it }
        Spacer(modifier = Modifier.height(16.dp))
        InputField("Email", email) { email = it }
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(password, passwordVisible) {
            passwordVisible = !passwordVisible
            password = it
        }

        Spacer(modifier = Modifier.height(11.dp))


        // Agreement Text
        AgreementText()

        Spacer(modifier = Modifier.height(30.dp))

        // Sign Up Button
        SignUpButton(username, email, password, context
            , navController, viewModel
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login Navigation
        LoginNavigation(
            navController
        )
    }
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.logo_zanahoria_naranja),
        contentDescription = "Logotipo Zanahoria",
        contentScale = ContentScale.Fit,
        modifier = Modifier.width(50.dp)
    )
}

@Composable
fun SignUpTitle() {
    Text(
        text = "Sign Up",
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}

@Composable
fun SignUpSubtitle() {
    Text(
        text = "Enter your credentials to continue",
        fontSize = 19.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Gray,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}

@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        colors = OverrideDefaultColors()
    )
}

@Composable
fun PasswordField(password: String, passwordVisible: Boolean, onValueChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            IconButton(onClick = { onValueChange(password) }) {
                Icon(imageVector = image, contentDescription = null)
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = OverrideDefaultColors()
    )
}

@Composable
fun AgreementText() {
    Column(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
        Row {
            Text(
                text = "By continuing you agree to our",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Terms of Service",
                color = Color.Green,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(7.dp))
        Row {
            Text(
                text = "and",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Privacy Policy.",
                color = Color.Green,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun SignUpButton(username: String, email: String, password: String, context: Context
                 , navController: NavController, viewModel: RegisterViewModel
) {
    Button(
        onClick = {
           viewModel.registerUser(username, email, password, context, navController)
        },
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF53B175)),
        contentPadding = PaddingValues(),
        modifier = Modifier.size(width = 350.dp, height = 60.dp)
    ) {
        Text("Sign Up", fontSize = 16.sp)
    }
}

@Composable
fun LoginNavigation(
    navController: NavController
) {
    Row {
        Text(text = "Already have an account?", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Sign In",
            color = Color.Green,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                navController.navigate(DestinationScreen.loginDest.route)
            }
        )
    }
}

@Composable
fun OverrideDefaultColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedTextColor = Color.Gray,
        unfocusedTextColor = Color.Gray,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        cursorColor = Color.Gray
    )
}