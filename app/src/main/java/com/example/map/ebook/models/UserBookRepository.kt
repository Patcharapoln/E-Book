package com.example.map.ebook.models

class UserBookRepository: BookRepository() {

    override fun loadAllBooks() {
        bookList.clear()
    }

}