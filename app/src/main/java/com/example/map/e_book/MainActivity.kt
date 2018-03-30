package com.example.map.e_book

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.map.e_book.models.Book
import com.example.map.e_book.models.BookRepository
import com.example.map.e_book.models.DataBookRepository
import com.example.map.e_book.models.MockBookRepository
import com.example.map.e_book.presenter.BookPresenter
import com.example.map.e_book.presenter.BookView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookView {

    var adapter: ArrayAdapter<Book>? = null
    lateinit var presenter: BookPresenter
    lateinit var repository: BookRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = DataBookRepository()
        presenter = BookPresenter(this, repository)
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1)
        presenter.start()
    }

    override fun setBookList(books: ArrayList<Book>) {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,books)
        bookList.adapter = adapter
    }

}
