package com.example.map.ebook.models

class MockBookRepository: BookRepository() {

    override fun loadAllBooks() {
        bookList.clear()
        bookList.add(Book(1, "BOOK1", 200.0, 2017, "https://imagery.pragprog.com/products/471/lhelph_largebeta.jpg"))
        bookList.add(Book(2, "BOOK2", 300.0,2019, "https://imagery.pragprog.com/products/444/rspec3_largebeta.jpg"))
        bookList.add(Book(3, "BOOK3", 400.0,2013, "https://imagery.pragprog.com/products/446/dswdcloj2_largecover.jpg"))
        setChanged()
        notifyObservers()
    }

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

}