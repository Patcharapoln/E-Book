package com.example.map.ebook.models

class CartBookRepository(val cartList: ArrayList<Book>): BookRepository() {

    override fun loadAllBooks() {
        bookList.clear()
        bookList.addAll(cartList)
        setChanged()
        notifyObservers()
    }

}