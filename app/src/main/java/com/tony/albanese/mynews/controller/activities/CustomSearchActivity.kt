package com.tony.albanese.mynews.controller.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.tony.albanese.mynews.R
import kotlinx.android.synthetic.main.search_screen_layout.*

class CustomSearchActivity : AppCompatActivity() {
    lateinit var searchButton: Button
    lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_screen_layout)

        searchButton = btn_search
        searchEditText = text_view_search_terms

        btn_search.setOnClickListener { view ->
            initiateSearch()
        }
    }

    fun initiateSearch() {
        var searchTerms = searchEditText.text.toString()
        Log.i("Method: ", "initiateSearch() called.")
        Log.i("Search terms:", searchTerms)
    }
}
