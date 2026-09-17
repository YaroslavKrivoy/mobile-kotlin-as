package com.example.kryvyi.android_project.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String?,
    var isRead: Boolean
)
