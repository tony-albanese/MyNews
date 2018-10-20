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
import android.widget.Toast
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.fragments.DatePickerFragment
import com.tony.albanese.mynews.controller.utilities.*
import kotlinx.android.synthetic.main.search_parameters_layout.*
import java.util.*
import kotlin.collections.HashMap

class CustomSearchActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var searchButton: Button
    lateinit var searchEditText: EditText
    lateinit var url: String
    lateinit var searchStartDate: String
    lateinit var searchEndDate: String
    lateinit var calendar: Calendar
    lateinit var newsDesksHashMap: HashMap<Int, String>

    var selectedTextViewId = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_parameters_layout)

        searchButton = btn_search
        searchEditText = text_view_search_terms

        calendar = Calendar.getInstance()
        searchStartDate = convertDate(calendar.time, SEARCH_DATE_FORMAT)
        searchEndDate = convertDate(calendar.time, SEARCH_DATE_FORMAT)
        newsDesksHashMap = HashMap()

        btn_search.setOnClickListener { view ->
            launchSearchActivity()
        }
    }

    fun launchSearchActivity() {
        var searchTerms = searchEditText.text.toString()

        if (searchTerms.isNullOrBlank() || searchTerms.isNullOrEmpty()) {
            val toast = Toast.makeText(this, "Please enter search terms", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            var newsDesks = generateNewsDeskParameter(newsDesksHashMap)


            val jsonParameters = createSearchParametersJson(searchTerms, searchStartDate, searchEndDate, newsDesks)
            url = generateSearchUrl(applicationContext, CUSTOM_SEARCH_SEARCH, jsonParameters)

            val preferences = applicationContext.getSharedPreferences(URL_SHARED_PREFERENCES, Context.MODE_PRIVATE)
            preferences.edit().putString(URL, url).apply()
            launchActivity()
        }


    }

    fun launchActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(TAB, CUSTOM_SEARCH_TAB)
        startActivity(intent)
    }

    fun createDatePicker(view: View) {
        selectedTextViewId = view.id
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        val date: Date = calendar.time

        when (selectedTextViewId) {
            R.id.tv_start_date -> {
                tv_start_date.text = convertDate(date)
                searchStartDate = convertDate(date, SEARCH_DATE_FORMAT)
            }

            R.id.tv_end_date -> {
                tv_end_date.text = convertDate(date)
                searchEndDate = convertDate(date, SEARCH_DATE_FORMAT)
            }
        }
    }

    fun checkboxOnClickListener(view: View) {
        when (view.id) {
            R.id.check_box_arts -> {
                if (check_box_arts.isChecked) {
                    newsDesksHashMap.put(R.id.check_box_arts, "Arts")
                } else {
                    newsDesksHashMap.remove(R.id.check_box_arts)
                }
            }

            R.id.check_box_business -> {
                if (check_box_business.isChecked) {
                    newsDesksHashMap.put(R.id.check_box_business, "Business")
                } else {
                    newsDesksHashMap.remove(R.id.check_box_business)
                }
            }

            R.id.check_box_editorial -> {
                if (check_box_editorial.isChecked) {
                    newsDesksHashMap.put(R.id.check_box_editorial, "Editorial")
                } else {
                    newsDesksHashMap.remove(R.id.check_box_editorial)
                }
            }

            R.id.check_box_financial -> {
                if (check_box_financial.isChecked) {
                    newsDesksHashMap.put(R.id.check_box_financial, "Financial")
                } else {
                    newsDesksHashMap.remove(R.id.check_box_financial)
                }
            }

            R.id.check_box_politics -> {
                if (check_box_politics.isChecked) {
                    newsDesksHashMap.put(R.id.check_box_politics, "Politics")
                } else {
                    newsDesksHashMap.remove(R.id.check_box_politics)
                }
            }

            R.id.check_box_science -> {
                if (check_box_science.isChecked) {
                    newsDesksHashMap.put(R.id.check_box_science, "Science")
                } else {
                    newsDesksHashMap.remove(R.id.check_box_science)
                }
            }

        }
    }

}
