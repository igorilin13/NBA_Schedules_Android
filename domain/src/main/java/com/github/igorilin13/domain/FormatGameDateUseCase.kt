package com.github.igorilin13.domain

import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FormatGameDateUseCase @Inject constructor(
    private val dateTextProvider: DateTextProvider
) {
    private val today = LocalDate.now()
    private val yesterday = today.minusDays(1)
    private val tomorrow = today.plusDays(1)

    private val hourOnlyFormat = DateTimeFormatter.ofPattern("h a")
    private val hourMinutesFormat = DateTimeFormatter.ofPattern("h:mm a")
    private val dateFormat = DateTimeFormatter.ofPattern("EEE, MMM d")

    operator fun invoke(date: ZonedDateTime): String {
        val timeFormatter = if (date.minute == 0) hourOnlyFormat else hourMinutesFormat

        val localDate = date.toLocalDate()
        return when {
            localDate == yesterday -> dateTextProvider.getYesterday()
            localDate < today -> date.format(dateFormat)
            localDate == today -> dateTextProvider.getToday(date.format(timeFormatter))
            localDate == tomorrow -> dateTextProvider.getTomorrow(date.format(timeFormatter))
            else -> date.format(dateFormat) + ", ${date.format(timeFormatter)}"
        }
    }
}