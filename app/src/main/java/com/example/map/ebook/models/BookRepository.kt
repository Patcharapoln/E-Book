package com.example.map.ebook.models

import java.util.*
import kotlin.collections.ArrayList

abstract class BookRepository: Observable() {

    val bookList = ArrayList<Book>()

    abstract fun loadAllBooks()
    abstract fun getBooks(): ArrayList<Book>

    fun filter(searchMsg: String): List<Book> {
        return bookList.filter { book: Book ->
            book.title.contains(searchMsg, true) || book.pub_year.toString().contains(searchMsg, true)
        }
    }
}