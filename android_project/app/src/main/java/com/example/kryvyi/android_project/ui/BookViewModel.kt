package com.example.kryvyi.android_project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kryvyi.android_project.BookTrackerApplication
import com.example.kryvyi.android_project.data.BookRepository
import com.example.kryvyi.android_project.model.Book
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private val initialBooks = listOf(
    Book(
        id = 1,
        title = "Clean Code",
        author = "Robert C. Martin",
        description =
            "A handbook of agile software craftsmanship.",
        isRead = true
    ),
    Book(
        id = 2,
        title = "Kotlin in Action",
        author =
            "Dmitry Jemerov, Svetlana Isakova",
        description = null,
        isRead = false
    ),
    Book(
        id = 3,
        title = "Refactoring",
        author = "Martin Fowler",
        description =
            "Improving the design of existing code.",
        isRead = false
    )
)

class BookViewModel(
    private val repository: BookRepository
) : ViewModel() {

    val uiState: StateFlow<BookListUiState> =
        repository
            .observeBooks()
            .map { books ->

                BookListUiState(
                    books = books
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BookListUiState()
            )


    fun addBook(
        title: String,
        author: String,
        description: String?,
        isRead: Boolean
    ) {

        val book = Book(
            id = 0,
            title = title,
            author = author,
            description = description,
            isRead = isRead
        )

        viewModelScope.launch {
            repository.addBook(
                book
            )
        }
    }


    fun toggleBookRead(
        bookId: Int
    ) {
        val book =
            uiState
                .value
                .books
                .find {
                    it.id == bookId
                }
                ?: return

        val updatedBook = book.copy(
                isRead = !book.isRead
            )

        viewModelScope.launch {
            repository.updateBook(
                updatedBook
            )
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = this[APPLICATION_KEY] as BookTrackerApplication
                    val repository = application.container.bookRepository

                    BookViewModel(
                        repository = repository
                    )
                }
            }
    }

}




