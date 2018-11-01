package com.tony.albanese.mynews.controller.fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val is24HourFormat = android.text.format.DateFormat.is24HourFormat(activity)
        return TimePickerDialog(context, activity as TimePickerDialog.OnTimeSetListener, hour, minute, is24HourFormat)
        //return super.onCreateDialog(savedInstanceState)
    }
}