package com.example.map.e_book.presenter

import com.example.map.e_book.models.BookRepository
import java.util.*

class BookPresenter(val view: BookView, val repository: BookRepository): Observer {
    fun start() {
        repository.addObserver(this)
        repository.loadAllBooks()
    }

    override fun update(o: Observable?, arg: Any?) {
        if(o == repository) {
            view.setBookList(repository.getBooks())
        }
    }
}