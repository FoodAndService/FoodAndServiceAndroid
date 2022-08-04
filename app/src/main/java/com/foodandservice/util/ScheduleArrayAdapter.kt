package com.foodandservice.util

import android.content.Context
import android.widget.ArrayAdapter

class ScheduleArrayAdapter(context: Context, resource: Int, objects: List<String>) :
    ArrayAdapter<String>(context, resource, objects) {

    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }
}