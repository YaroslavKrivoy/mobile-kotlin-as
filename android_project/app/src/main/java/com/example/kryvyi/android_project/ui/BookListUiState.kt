package com.example.kryvyi.android_project.ui

import com.example.kryvyi.android_project.model.Book

data class BookListUiState(
    val books: List<Book> =
        emptyList()
)

