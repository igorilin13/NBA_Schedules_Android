package com.github.igorilin13.domain

interface DateTextProvider {
    fun getYesterday(): String

    fun getToday(time: String): String

    fun getTomorrow(time: String): String
}