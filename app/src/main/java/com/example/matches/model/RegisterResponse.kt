package com.example.matches.model

data class RegisterResponse(
    val customMsg: Any,
    val errorMsg: Any,
    val idKey: Int,
    val isSave: Boolean,
    val keyId: Any
)