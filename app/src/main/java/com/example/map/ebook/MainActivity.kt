package com.example.map.ebook

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.view.MenuItem
import com.example.map.ebook.fragment.BookStoreFragment
import com.example.map.ebook.fragment.MockUserFragment
import com.example.map.ebook.models.Book
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.booklist_fragment.*

class MainActivity : AppCompatActivity() {

    private val INPUT_REQUEST_CODE = 100
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private val bookStoreFragment: BookStoreFragment = BookStoreFragment()
    private val mockUserFragment: MockUserFragment = MockUserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId) {
        R.id.cart_menu -> {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("cartList", bookStoreFragment.getCartList())
            startActivityForResult(intent, INPUT_REQUEST_CODE)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == INPUT_REQUEST_CODE) {
            if(resultCode != Activity.RESULT_CANCELED) {
                if(data != null) {
                    //When close cart activity
                    val cartList = bookStoreFragment.getCartList()
                    mockUserFragment.addBook(cartList)
                    bookStoreFragment.getCartList().clear()
                }
            }
        }
    }

    inner class SectionsPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return bookStoreFragment
                1 -> return mockUserFragment
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }
    }

}
