package com.github.igorilin13.domain.date

interface DateTextProvider {
    fun getYesterday(): String

    fun getToday(time: String): String

    fun getTomorrow(time: String): String
}