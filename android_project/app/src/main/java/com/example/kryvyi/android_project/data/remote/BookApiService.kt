package com.example.kryvyi.android_project.data.remote

import com.example.kryvyi.android_project.data.remote.dto.BooksResponseDto
import retrofit2.http.GET

interface BookApiService {

    @GET("books/")
    suspend fun getBooks(): BooksResponseDto
}