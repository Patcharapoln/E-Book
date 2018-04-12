package com.example.map.ebook.presenter

import com.example.map.ebook.models.Book

interface BookView {
     fun setBookList(books: ArrayList<Book>)
}
