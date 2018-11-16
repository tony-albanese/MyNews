package com.tony.albanese.mynews.controller.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.fragments.TimePickerFragment
import com.tony.albanese.mynews.controller.utilities.*
import com.tony.albanese.mynews.model.SearchAlarmReceiver
import kotlinx.android.synthetic.main.search_parameters_layout.*
import java.util.*

class NotificationActivity : AppCompatActivity(), OnTimeSetListener {
    lateinit var calendar: Calendar
    lateinit var searchEditText: EditText

    var newsDesksHashMap = HashMap<Int, String>()
    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_parameters_layout)

        calendar = Calendar.getInstance()
        searchEditText = findViewById(R.id.text_view_search_terms)

        //Set the visibility for the components on this Activity.
        btn_search.visibility = View.GONE
        tv_start_date.visibility = View.INVISIBLE
        tv_end_date.visibility = View.INVISIBLE
        tv_notification.visibility = View.VISIBLE
        switch_auto_search.visibility = View.VISIBLE
        btn_notification_confirm.visibility = View.VISIBLE

        setupTextChangeListener()

        //Set the click listener for the textview.
        tv_notification.setOnClickListener {
            val timePicker = TimePickerFragment()
            timePicker.show(supportFragmentManager, "time picker")
        }

        //Set the click listener for the button. The activity is destroyed when the button is pressed.
        btn_notification_confirm.setOnClickListener {
            if (switch_auto_search.isChecked) {
                startSearchAlarm(calendar)
            } else {
                cancelSearchAlarm()
            }
            finish()
        }
    }

    //Restore the state when the activity is resumed.
    override fun onResume() {
        restoreActivityState()
        setupTextChangeListener()
        setConfirmationButtonClickable()
        super.onResume()
    }

    //Save on pause.
    override fun onPause() {
        saveActivityState()
        super.onPause()
    }

    //Save the state
    override fun onDestroy() {
        saveActivityState()
        super.onDestroy()
    }
    
    fun startSearchAlarm(c: Calendar) {
        val currentCalendar = Calendar.getInstance()
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, SearchAlarmReceiver::class.java)
        //intent.putExtra("notification_url", createIntentUrl())
        setIntentExtras(intent)
        if (c.before(currentCalendar)) c.timeInMillis += 24 * 60 * 60 * 1000
        val pendingIntent = PendingIntent.getBroadcast(this, SEARCH_ALARM_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    fun cancelSearchAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, SearchAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, SEARCH_ALARM_CODE, intent, 0)
        alarmManager.cancel(pendingIntent)
    }

    //Callback for when the user sets a time for notifictions.
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val toast = Toast.makeText(this, "Time Set", Toast.LENGTH_SHORT)
        toast.show()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        //Check if the alarm switch is checked. If it is, start the alarm.
        if (switch_auto_search.isChecked) {
            startSearchAlarm(calendar)
        }
    }

    //Set the checkboxClickListener to add a news desk to the hashmap when the user clicks
    //and remove it when it is unchecked.
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
        setConfirmationButtonClickable()
    }

    //This function is run when the user changes the text in the search box.
    fun setupTextChangeListener() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setConfirmationButtonClickable()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    //This function checks the conditions that allow the save button to be clickable.
    fun setConfirmationButtonClickable() {
        if (searchEditText.text.isNotEmpty() && searchEditText.text.isNotBlank() && newsDesksHashMap.isNotEmpty()) {
            btn_notification_confirm.isEnabled = true
            switch_auto_search.isEnabled = true
        } else {
            btn_notification_confirm.isEnabled = false
        }
    }

    //This function saves the state of the Activity.
    fun saveActivityState() {
        val notificationActivityPreferences = getSharedPreferences(NOTIFICATION_PREFERENCES, Context.MODE_PRIVATE)
        with(notificationActivityPreferences.edit()) {
            putString("search_terms", searchEditText.text.toString())
            putString(NEWS_DESK_HASH_MAP, mapToString(newsDesksHashMap))
            putBoolean("arts_box_checked", check_box_arts.isChecked)
            putBoolean("business_box_checked", check_box_business.isChecked)
            putBoolean("editorial_box_checked", check_box_editorial.isChecked)
            putBoolean("financial_box_checked", check_box_financial.isChecked)
            putBoolean("politics_box_checked", check_box_politics.isChecked)
            putBoolean("science_box_checked", check_box_science.isChecked)
            putBoolean("switch_state_checked", switch_auto_search.isChecked)
            putBoolean("switch_state_enabled", switch_auto_search.isEnabled)
            putBoolean("confirmation_button_enabled", btn_notification_confirm.isEnabled)
            putLong("calendar_time", calendar.timeInMillis)
            apply()
        }
    }

    //This function restores the state of the Activity.
    fun restoreActivityState() {
        val prefs = getSharedPreferences(NOTIFICATION_PREFERENCES, Context.MODE_PRIVATE)
        val c = Calendar.getInstance()
        searchEditText.setText(prefs.getString("search_terms", ""))
        check_box_arts.isChecked = prefs.getBoolean("arts_box_checked", false)
        check_box_business.isChecked = prefs.getBoolean("business_box_checked", false)
        check_box_financial.isChecked = prefs.getBoolean("financial_box_checked", false)
        check_box_politics.isChecked = prefs.getBoolean("politics_box_checked", false)
        check_box_science.isChecked = prefs.getBoolean("science_box_checked", false)
        check_box_editorial.isChecked = prefs.getBoolean("editorial_box_checked", false)
        switch_auto_search.isChecked = prefs.getBoolean("switch_state_checked", false)
        switch_auto_search.isEnabled = prefs.getBoolean("switch_state_enabled", false)
        btn_notification_confirm.isEnabled = prefs.getBoolean("confirmation_button_enabled", false)
        calendar.timeInMillis = prefs.getLong("calendar_time", c.timeInMillis)
        newsDesksHashMap.clear()
        newsDesksHashMap = getMapFromPrefereces(prefs, NEWS_DESK_HASH_MAP)
    }

    //This function creates the url for the Intent that will run when the user presses the save button.
    fun setIntentExtras(i: Intent) {
        var searchTerms = searchEditText.text.toString()
        var newsDesks = generateNewsDeskParameter(newsDesksHashMap)

        i.putExtra("search_terms", searchTerms)
        i.putExtra("news_desks", newsDesks)
    }
}
