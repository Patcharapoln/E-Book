package com.example.map.ebook.models

class Book(val id: Int, val title: String, val price: Double = 0.0, val pub_year: Int = 0, val imageURL: String = "") {

    override fun toString(): String {
        return "Title:    ${title}\n" +
                "Price:   ${price}\n" +
                "Year:    ${pub_year}"
    }

}