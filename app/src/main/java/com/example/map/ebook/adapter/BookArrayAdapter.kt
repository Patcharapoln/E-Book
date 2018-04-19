package com.example.map.ebook.adapter

import android.app.AlertDialog
import android.app.FragmentManager
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.*
import com.example.map.ebook.R
import com.example.map.ebook.models.Book
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.example.map.ebook.fragment.AddCartDialogFragment
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL


class BookArrayAdapter(context: Context, list: ArrayList<Book>): BaseAdapter(), ListAdapter{

    private val list = list
    private val context: Context? = context

    lateinit var bookImage: ImageView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.lineitem, null)
        }

        //Handle TextView and display string from your list
        val bookTitleText = view!!.findViewById(R.id.book_title) as TextView
        bookTitleText.text = list[position].toString()

        val bookPriceText = view!!.findViewById(R.id.book_price) as TextView
        bookPriceText.text = "Price: " + list[position].price

        bookImage = view!!.findViewById(R.id.book_image) as ImageView
//        var input: InputStream? = null
//        try {
//            println(list[position].img_url+ "---------------------------------------------")
//            val url = URL(list[position].img_url)
//            input = BufferedInputStream(url.openStream())
//            bookImage.setImageBitmap(BitmapFactory.decodeStream(input))
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            if (input != null) {
//                try {
//                    input!!.close()
//                } catch (ignored: IOException) {
//                    // Nothing to do
//                }
//
//            }
//        }
//        ImageWorker(position).execute()

        //Handle buttons and add onClickListeners
        val addBtn = view.findViewById(R.id.add_btn) as Button

        addBtn.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this.context)
            builder.setMessage("Add this book to cart ?")
                    .setPositiveButton("ADD", DialogInterface.OnClickListener { dialog, id ->
                        println(list[position].title)
                    })
                    .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, id ->
                        // Do nothing
                    })
            // Create the AlertDialog object and return it
            builder.create().show()
            notifyDataSetChanged()
        })

        return view
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

//    private inner class ImageWorker(position: Int) : AsyncTask<String, Void, Bitmap>() {
//
//        val position = position
//
//        /**
//         * {@inheritDoc}
//         */
//        override fun doInBackground(vararg params: String): Bitmap? {
//            var input: InputStream? = null
//            try {
//                println(list[position].img_url+ "---------------------------------------------")
//                val url = URL(list[position].img_url)
//                input = BufferedInputStream(url.openStream())
//                return BitmapFactory.decodeStream(input)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            } finally {
//                if (input != null) {
//                    try {
//                        input!!.close()
//                    } catch (ignored: IOException) {
//                        // Nothing to do
//                    }
//
//                }
//            }
//            return null
//        }
//
//        /**
//         * {@inheritDoc}
//         */
//        override fun onPostExecute(result: Bitmap?) {
//            if (result != null) {
//                println("FINISH DOWNLOAD IMAGE")
//                bookImage.setImageBitmap(result)
//            }
//        }
//    }

}
