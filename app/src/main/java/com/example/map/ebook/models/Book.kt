package com.example.map.ebook.models

class Book(val id: Int, val title: String, val price: Double = 0.0, val pub_year: Int = 0, val img_url: String = "") {

    override fun toString(): String {
        return String.format("%s (%d)", title, pub_year)
    }

}