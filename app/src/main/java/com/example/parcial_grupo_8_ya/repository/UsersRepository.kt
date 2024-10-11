package com.example.parcial_grupo_8_ya.repository

import com.example.parcial_grupo_8_ya.api.UsersApi
import com.example.parcial_grupo_8_ya.data.model.User.UsersList
import com.google.firebase.firestore.auth.User
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val api: UsersApi

){
    suspend fun getUsers() : UsersList {
        return api.getUsers()
    }

}