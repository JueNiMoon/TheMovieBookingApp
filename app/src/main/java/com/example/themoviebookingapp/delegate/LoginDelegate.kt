package com.example.themoviebookingapp.delegate

interface LoginDelegate {

    fun login(email: String, password: String)

    fun signWithEmail(name : String, email : String, phone : String, password : String)
}