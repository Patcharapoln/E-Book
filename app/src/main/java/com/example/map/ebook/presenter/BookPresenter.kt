package com.example.map.ebook.presenter

import com.example.map.ebook.models.Book
import com.example.map.ebook.models.BookRepository
import java.util.*
import kotlin.collections.ArrayList

class BookPresenter(val view: BookView, val repository: BookRepository): Observer {

    fun start() {
        repository.addObserver(this)
        repository.loadAllBooks()
    }

    fun search(searchMsg: String, sortBy: String) {
        view.setBookList(repository.filter(searchMsg, sortBy) as ArrayList<Book>)
    }

    override fun update(o: Observable?, arg: Any?) {
        if(o == repository) {
            view.setBookList(repository.getBooks())
        }
    }
}