package com.example.map.ebook.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.map.ebook.R
import com.example.map.ebook.adapter.BookArrayAdapter
import com.example.map.ebook.models.Book
import com.example.map.ebook.models.BookRepository
import com.example.map.ebook.models.MockBookRepository
import com.example.map.ebook.models.UserBookRepository
import com.example.map.ebook.presenter.BookPresenter
import com.example.map.ebook.presenter.BookView
import kotlinx.android.synthetic.main.user_fragment.*
import kotlinx.android.synthetic.main.user_fragment.view.*

class MockUserFragment: Fragment(), BookView {

    private var listViewAdapter: BookArrayAdapter? = null
    private var spinnerAdapter: ArrayAdapter<String>? = null
    lateinit var presenter: BookPresenter
    lateinit var repository: BookRepository
    lateinit var rootView: View

    private var money = 100

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.user_fragment, container, false)
        repository = UserBookRepository()
        presenter = BookPresenter(this, repository)
        presenter.start()
        spinnerAdapter = ArrayAdapter(rootView.context,android.R.layout.simple_list_item_1)
        setSearchView()
        setSpinner()
        setMoneyLeft()
        return rootView
    }

    private fun setSearchView() {
        rootView.searchView1.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null)
                    presenter.search(searchView1.query.toString(), spinner1.selectedItem.toString())
                return false
            }
        })
    }

    private fun setSpinner() {
        spinnerAdapter = ArrayAdapter(rootView.context, android.R.layout.simple_spinner_item, arrayListOf("UNSORTED", "TITLE", "YEAR"))
        spinnerAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rootView.spinner1.adapter = spinnerAdapter
        rootView.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.search(searchView1.query.toString(), spinner1.selectedItem.toString())
            }
        }
    }

    override fun setBookList(books: ArrayList<Book>) {
        listViewAdapter = BookArrayAdapter(rootView.context, books)
        rootView.bookList1.adapter = listViewAdapter
    }

    private fun setMoneyLeft() {
        rootView.money.setText(money.toString())
    }

    fun addBook(bookList: ArrayList<Book>) {
        presenter.addBook(bookList)
    }
}