package com.example.map.ebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.map.ebook.models.Book
import com.example.map.ebook.models.BookRepository
import com.example.map.ebook.models.DataBookRepository
import com.example.map.ebook.presenter.BookPresenter
import com.example.map.ebook.presenter.BookView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookView {

    var listViewAdapter: ArrayAdapter<Book>? = null
    var spinnerAdapter: ArrayAdapter<String>? = null
    lateinit var presenter: BookPresenter
    lateinit var repository: BookRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = DataBookRepository()
        presenter = BookPresenter(this, repository)
        listViewAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1)
        spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1)
        presenter.start()

        setSearchView()
        setSpinner()
    }

    override fun setBookList(books: ArrayList<Book>) {
        listViewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,books)
        bookList.adapter = listViewAdapter
    }

    private fun setSearchView() {
        searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null)
                    presenter.search(searchView.query.toString(), spinner.selectedItem.toString())
                return false
            }
        })
    }

    private fun setSpinner() {
        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf("UNSORTED", "TITLE", "YEAR"))
        spinnerAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.search(searchView.query.toString(), spinner.selectedItem.toString())
            }
        }
    }
}
