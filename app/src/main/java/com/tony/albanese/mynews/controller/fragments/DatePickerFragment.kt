package com.tony.albanese.mynews.controller.fragments


import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.tony.albanese.mynews.controller.activities.CustomSearchActivity
import java.util.*


class DatePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity!!, activity as CustomSearchActivity, year, month, day)

    }


}
