package com.example.parcial_grupo_8_ya.api

import com.example.parcial_grupo_8_ya.data.model.User.UsersList
import com.example.parcial_grupo_8_ya.data.model.User.UsersListItem
import com.example.parcial_grupo_8_ya.repository.UsersRepository
import com.example.parcial_grupo_8_ya.utils.Constants.BASE_URL
import com.google.firebase.firestore.auth.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersApi {
    @GET("users")
    suspend fun getUsers(): UsersList

    @FormUrlEncoded
    @POST("users")
    fun createUser(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UsersListItem>

    @FormUrlEncoded
    @POST("auth/login")
    fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<UsersListItem>
}


