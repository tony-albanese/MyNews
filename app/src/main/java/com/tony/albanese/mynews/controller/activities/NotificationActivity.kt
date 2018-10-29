package com.tony.albanese.mynews.controller.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tony.albanese.mynews.R
import kotlinx.android.synthetic.main.search_parameters_layout.*

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_parameters_layout)

        btn_search.visibility = View.GONE
        tv_start_date.visibility = View.INVISIBLE
        tv_end_date.visibility = View.INVISIBLE
        tv_notification.visibility = View.VISIBLE
        switch_auto_search.visibility = View.VISIBLE
    }
}
