package com.example.map.e_book.models

import java.util.*
import kotlin.collections.ArrayList

abstract class BookRepository: Observable() {
    abstract fun loadAllBooks()
    abstract fun getBooks(): ArrayList<Book>
}