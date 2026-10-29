package com.example.kryvyi.android_project.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BookDto(

    val id: Int,

    val title: String,

    val authors: List<AuthorDto> = emptyList(),

    val summaries: List<String> = emptyList()
)