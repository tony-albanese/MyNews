package com.tony.albanese.mynews.controller.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.fragments.DatePickerFragment
import kotlinx.android.synthetic.main.search_screen_layout.*
import java.util.*


class CustomSearchActivity : AppCompatActivity() {
    lateinit var searchButton: Button
    lateinit var searchEditText: EditText
    lateinit var datePicker: DatePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_screen_layout)

        searchButton = btn_search
        searchEditText = text_view_search_terms

        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var dat = calendar.get(Calendar.DAY_OF_MONTH)

        btn_search.setOnClickListener { view ->
            initiateSearch()
            launchActivity()
        }
    }

    fun initiateSearch() {
        var searchTerms = searchEditText.text.toString()
        Log.i("Method: ", "initiateSearch() called.")
        Log.i("Search terms:", searchTerms)
    }

    fun launchActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("TAB", 3)
        startActivity(intent)
    }

    fun createDatePicker(view: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun getDate() {


    }
}
