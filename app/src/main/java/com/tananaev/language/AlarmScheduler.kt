package com.tananaev.language

import java.util.*

object AlarmScheduler {

    private const val hourFrom = 8
    private const val hourTo = 22

    fun checkTime(): Boolean {

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return hour in hourFrom..hourTo
    }

    fun nextAlarmTime(): Long {

        val calendar = Calendar.getInstance()
        if (calendar.get(Calendar.MINUTE) > 30) {
            calendar.add(Calendar.HOUR_OF_DAY, 2)
        } else {
            calendar.add(Calendar.HOUR_OF_DAY, 1)
        }
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        if (calendar.get(Calendar.HOUR_OF_DAY) > hourTo) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            calendar.set(Calendar.HOUR_OF_DAY, hourFrom)
        }

        return calendar.timeInMillis
    }
}
