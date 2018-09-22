package com.tony.albanese.mynews.controller.activities

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.fragments.DatePickerFragment
import com.tony.albanese.mynews.controller.utilities.convertDate
import com.tony.albanese.mynews.controller.utilities.createSearchParametersJson
import com.tony.albanese.mynews.controller.utilities.generateSearchUrl
import kotlinx.android.synthetic.main.search_screen_layout.*
import java.util.*


class CustomSearchActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var searchButton: Button
    lateinit var searchEditText: EditText
    lateinit var datePicker: DatePicker
    lateinit var url: String
    lateinit var searchStartDate: String
    lateinit var searchEndDate: String
    lateinit var selectedDate: String
    lateinit var calendar: Calendar

    val SEARCH_DATE_FORMAT = "yyyMMdd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_screen_layout)

        searchButton = btn_search
        searchEditText = text_view_search_terms

        calendar = Calendar.getInstance()
        selectedDate = convertDate(calendar.time)
        searchStartDate = convertDate(calendar.time, SEARCH_DATE_FORMAT)
        searchEndDate = convertDate(calendar.time, SEARCH_DATE_FORMAT)

        btn_search.setOnClickListener { view ->
            initiateSearch()
            launchActivity()
        }
    }

    fun initiateSearch() {
        var searchTerms = searchEditText.text.toString()
        val newsDesks = "Politics Science"
        val jsonParameters = createSearchParametersJson(searchTerms, searchStartDate, searchEndDate, newsDesks)
        url = generateSearchUrl(applicationContext, 4, jsonParameters)

        val preferences = applicationContext.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        preferences.edit().putString("URL", url).apply()
    }

    fun launchActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("TAB", 3)
        startActivity(intent)
    }

    fun createDatePicker(view: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")

        when (view.id) {
            R.id.tv_start_date -> {
                tv_start_date.text = selectedDate
                searchStartDate = convertDate(calendar.time, SEARCH_DATE_FORMAT)

            }

            R.id.tv_end_date -> {
                tv_end_date.text = selectedDate
                searchEndDate = convertDate(calendar.time, SEARCH_DATE_FORMAT)
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        val date: Date = calendar.time
        selectedDate = convertDate(date)
    }

}
