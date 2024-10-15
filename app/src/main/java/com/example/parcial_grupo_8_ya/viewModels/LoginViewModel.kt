package com.example.parcial_grupo_8_ya.viewModels

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.parcial_grupo_8_ya.api.RetrofitClient
import com.example.parcial_grupo_8_ya.data.model.User.UsersListItem
import com.example.parcial_grupo_8_ya.screen.login.Credentials
import com.example.parcial_grupo_8_ya.screen.splash.DestinationScreen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel  {

    // API call para chequear credenciales
    fun checkCredentials(credentials: Credentials, context: Context
                                 , navController: NavController
    ) {
        RetrofitClient.instance.userLogin(credentials.login, credentials.pwd).enqueue(object :
            Callback<UsersListItem> {
            override fun onResponse(call: Call<UsersListItem>, response: Response<UsersListItem>) {
                if (response.isSuccessful && (credentials.login.isNotEmpty() && credentials.pwd.isNotEmpty())) {
                    Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(DestinationScreen.shopDest.route)

                } else  {
                    Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UsersListItem>, t: Throwable) {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })

    }
}