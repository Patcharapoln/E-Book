package com.example.map.e_book.presenter

import com.example.map.e_book.models.Book

interface BookView {
     fun setBookList(books: ArrayList<Book>)
}
