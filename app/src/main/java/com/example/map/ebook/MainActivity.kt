package com.example.map.ebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.map.ebook.models.Book
import com.example.map.ebook.models.BookRepository
import com.example.map.ebook.models.DataBookRepository
import com.example.map.ebook.presenter.BookPresenter
import com.example.map.ebook.presenter.BookView
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
        searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null)
                    presenter.search(searchView.query.toString())
                return false
            }
        })
    }

    override fun setBookList(books: ArrayList<Book>) {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,books)
        bookList.adapter = adapter
    }
}
