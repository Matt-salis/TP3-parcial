@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.parcial_grupo_8_ya.screen.login

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.parcial_grupo_8_ya.screen.register.OverrideDefaultColors
import com.example.parcial_grupo_8_ya.screen.splash.DestinationScreen
import com.example.parcial_grupo_8_ya.viewModels.LoginViewModel
import com.example.parcial_grupo_8_ya.ui.component.CommonButton
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun LoginForm(
    navController: NavController
) {
     val viewModel = LoginViewModel()
    Surface {
        GradientBackground()
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var credentials by remember { mutableStateOf(Credentials()) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Logo()
            Spacer(modifier = Modifier.height(30.dp))
            Title("Sign In")
            Spacer(modifier = Modifier.height(15.dp))
            Subtitle("Enter your email and password")
            Spacer(modifier = Modifier.height(16.dp))

            // Email Field
            LoginField(
                value = credentials.login,
                onChange = { data -> credentials = credentials.copy(login = data) }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            PasswordField(
                value = credentials.pwd,
                onChange = { data -> credentials = credentials.copy(pwd = data) },
                submit = { coroutineScope.launch { viewModel.checkCredentials(credentials, context
                    , navController
                ) } }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Forgot Password
            ForgotPassword { /* Handle forgot password action */ }
            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            CommonButton(
                text = "Log In",
                onClick = { coroutineScope.launch { viewModel.checkCredentials(credentials, context
                    , navController
                ) } }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Sign Up CTA
            SignUpPrompt(
                navController
            )
        }
    }
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.logo_zanahoria_naranja),
        contentDescription = "Logo Zanahoria",
        modifier = Modifier.size(50.dp)
    )
}

@Composable
fun Title(text: String) {
    Text(
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}

@Composable
fun Subtitle(text: String) {
    Text(
        text = text,
        fontSize = 19.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Gray,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}

@Composable
fun ForgotPassword(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Forgot Password?",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier
                .clickable(onClick = onClick)
                .align(Alignment.CenterEnd).padding(8.dp)
        )
    }
}

@Composable
fun SignUpPrompt(
    navController: NavController
) {
    Row {
        Text(text = "Don't have an account?", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Signup",
            color = Color.Green,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
            navController.navigate(DestinationScreen.signupDest.route)
            }
        )
    }
}



data class Credentials(var login: String = "", var pwd: String = "") {
    fun isNotEmpty(): Boolean {
        return login.isNotEmpty() && pwd.isNotEmpty()
    }
}

@Composable
fun LoginField(value: String, onChange: (String) -> Unit) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        colors = OverrideDefaultColors(),
        placeholder = { Text("Enter your Email") },
        label = { Text("Email") },
        singleLine = true
    )
}

@Composable
fun PasswordField(value : String,
                  onChange : (String)->Unit,
                  submit : ()->Unit,
                  modifier: Modifier = Modifier,
                  label : String = "Password",
                  placeholder : String = "Enter your Password"){

    var isPasswordVisible by remember { mutableStateOf(false) }

//    val leadingIcon = @Composable {
//        Icon(
//            Icons.Default.Key,
//            contentDescription = "",
//            tint = MaterialTheme.colorScheme.primary)
//    }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }, interactionSource = remember { MutableInteractionSource() }) {
            Icon(
                if(isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary)
        }
    }


    TextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth(),
//        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(
            onDone = {submit()}
        ),
        colors = OverrideDefaultColors(),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

    )
}


@Composable
fun ButtonField(text : String, onClick : ()->Unit){
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF53B175)),
        contentPadding = PaddingValues(),
        interactionSource = remember { MutableInteractionSource() },
        modifier = Modifier.size(width = 350.dp, height = 60.dp)
    )
    {
        Text(
            text = text,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun GradientBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.gradient_background),
            contentDescription = "Gradient Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}