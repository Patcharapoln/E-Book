package com.example.map.ebook

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.map.ebook.adapter.BookArrayAdapter
import com.example.map.ebook.models.Book
import com.example.map.ebook.models.BookRepository
import com.example.map.ebook.models.CartBookRepository
import com.example.map.ebook.presenter.BookPresenter
import com.example.map.ebook.presenter.BookView
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity: AppCompatActivity(), BookView {

    private lateinit var cartList: ArrayList<Book>
    private lateinit var listViewAdapter: BookArrayAdapter
    lateinit var presenter: BookPresenter
    lateinit var repository: BookRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartList = intent.getSerializableExtra("cartList") as ArrayList<Book>
        repository = CartBookRepository(cartList)
        presenter = BookPresenter(this, repository)
        presenter.start()

        val checkoutBtn = this.findViewById(R.id.checkout_button) as Button
        checkoutBtn.setOnClickListener({
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure you want to buy all this books ?")
                    .setPositiveButton("BUY", DialogInterface.OnClickListener { dialog, id ->
                        val intent = Intent()
                        intent.putExtra("cartList",cartList)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    })
                    .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
            // Create the AlertDialog object and return it
            builder.create().show()
        })
    }

    override fun setBookList(books: ArrayList<Book>) {
        listViewAdapter = BookArrayAdapter(this, books)
        bookList.adapter = listViewAdapter
    }

}