package com.example.kryvyi.android_project.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BooksResponseDto(
    val results: List<BookDto>
)