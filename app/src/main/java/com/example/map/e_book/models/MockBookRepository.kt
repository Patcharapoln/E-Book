package com.example.map.e_book.models

class MockBookRepository: BookRepository() {

    val bookList = ArrayList<Book>()

    override fun loadAllBooks() {
        bookList.clear()
        bookList.add(Book(1, "BOOK1", 200.0))
        bookList.add(Book(2, "BOOK2", 300.0))
        bookList.add(Book(3, "BOOK3", 400.0))
        setChanged()
        notifyObservers()

    }

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

}