@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.parcial_grupo_8_ya.screen.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_8_ya.MainActivity
import com.example.parcial_grupo_8_ya.data.model.User.UsersList
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.R
import com.example.parcial_grupo_8_ya.api.RetrofitClient
import com.example.parcial_grupo_8_ya.data.model.User.UsersListItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
@Preview
fun LoginForm() {


    Surface{
        GradientBackground()


        var credentials by remember { mutableStateOf(Credentials()) }
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()



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
                text = "Sign In",
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Enter your emails and password",
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(16.dp))

            LoginField(
                value = credentials.login,
                onChange = {data -> credentials = credentials.copy(login = data)},
                modifier = Modifier.background(Color.Transparent)
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordField(value = credentials.pwd,
                onChange = {data -> credentials = credentials.copy(pwd = data)},
                submit = { coroutineScope.launch { CheckCredentials(credentials, context) } },
                modifier = Modifier.background(Color.Transparent)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Forgot Password?",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { /* Acción de olvido de contraseña */ }
            )


            Spacer(modifier = Modifier.height(16.dp))

            ButtonField(text = "Login In",{ coroutineScope.launch { CheckCredentials(credentials, context) } })

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text(text = "Don't have an account?", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Signup",
                    color = Color.Green,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { /* Acción de registro */ }
                )
            }


        }
    }
}

fun CheckCredentials(credentials: Credentials, context : Context){
    if(credentials.isNotEmpty()){
        RetrofitClient.instance.userLogin(credentials.login,credentials.pwd)
            .enqueue(object: Callback<UsersListItem>{
                override fun onResponse(
                    call: Call<UsersListItem>,
                    response: Response<UsersListItem>
                ) {
                    if(response.isSuccessful()) {
                        Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show()
                    }
                  else {
                        Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
                  }
                }

                override fun onFailure(call: Call<UsersListItem>, t: Throwable) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }

            })

    }
}

data class Credentials(var login : String = "", var pwd : String = "", var remember : Boolean = false) {
    fun isNotEmpty(): Boolean {
        return login.isNotEmpty() && pwd.isNotEmpty()
    }

}


@Composable
fun LoginField(value : String,
               onChange : (String)->Unit,
               modifier: Modifier = Modifier,
               label : String = "Email",
               placeholder : String = "Enter your Email"){

    val focusManager = LocalFocusManager.current
//    val leadingIcon = @Composable {
//        Icon(
//            Icons.Default.Person,
//            contentDescription = "",
//            tint = MaterialTheme.colorScheme.primary)
//    }


    TextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth(),
//        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
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