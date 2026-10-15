package com.example.kryvyi.android_project.data

import com.example.kryvyi.android_project.data.local.BookEntity
import com.example.kryvyi.android_project.model.Book

fun BookEntity.toBook(): Book =
    Book(
        id = id,
        title = title,
        author = author,
        description = description,
        isRead = isRead
    )

fun Book.toEntity(): BookEntity =
    BookEntity(
        id = id,
        title = title,
        author = author,
        description = description,
        isRead = isRead
    )
