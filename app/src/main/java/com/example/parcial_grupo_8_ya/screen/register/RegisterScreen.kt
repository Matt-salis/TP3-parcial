package com.example.parcial_grupo_8_ya.screen.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.api.RetrofitClient
import com.example.parcial_grupo_8_ya.data.model.User.UsersListItem
import com.example.parcial_grupo_8_ya.screen.login.GradientBackground
import com.example.parcial_grupo_8_ya.ui.theme.Parcial_grupo_8_YATheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RegisterScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    GradientBackground()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_zanahoria_naranja),
            contentDescription = "Logotipo Zanahoria",
            contentScale = ContentScale.Fit,
            modifier = Modifier.width(50.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Sign Up",
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Enter your credentials to continue",
            fontSize = 19.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth().background(Color.Transparent)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().background(Color.Transparent)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Default.Visibility
                else Icons.Default.VisibilityOff

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth().background(Color.Transparent)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Row {
                Text(
                    text = "By continuing you agree to our",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    // modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,

                    )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Terms of Service",
                    color = Color.Green,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            Row {
                Text(
                    text = "and",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    // modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,

                    )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Privacy Policy.",
                    color = Color.Green,
                    fontWeight = FontWeight.Medium,
                    //modifier = Modifier.fillMaxWidth(),
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                    RetrofitClient.instance.createUser(username, email, password).enqueue(object: Callback<UsersListItem>{
                        override fun onResponse(
                            call: Call<UsersListItem>,
                            response: Response<UsersListItem>
                        ) {
                                Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(call: Call<UsersListItem>, t: Throwable) {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        }

                    })
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF53B175)),
            contentPadding = PaddingValues(),
            modifier = Modifier.size(width = 350.dp, height = 60.dp)
        ) {
            Text("Sign Up",
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(text = "Already have an account?", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sing In",
                color = Color.Green,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { /* Acción de registro */ }
            )
        }
    }
}
