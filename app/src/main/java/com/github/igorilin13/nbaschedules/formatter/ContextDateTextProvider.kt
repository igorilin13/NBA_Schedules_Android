package com.github.igorilin13.nbaschedules.formatter

import android.content.Context
import com.github.igorilin13.domain.DateTextProvider
import com.github.igorilin13.nbaschedules.R
import javax.inject.Inject

class ContextDateTextProvider @Inject constructor(private val context: Context) : DateTextProvider {
    override fun getYesterday(): String {
        return context.getString(R.string.yesterday)
    }

    override fun getToday(time: String): String {
        return context.getString(R.string.today, time)
    }

    override fun getTomorrow(time: String): String {
        return context.getString(R.string.tomorrow, time)
    }
}