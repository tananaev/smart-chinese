package com.tananaev.language

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        const val ALARM_TAG = "alarm"
        const val NOTIFICATION_ID = 1
    }

    private lateinit var data: List<Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data = DataLoader.load(this, R.raw.hsk1)

        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.button_next).setOnClickListener {
            updateView(data[Random.nextInt(0, data.size)])
        }

        setRepeatedAlarm()
    }

    override fun onResume() {
        super.onResume()

        updateView(data[Random.nextInt(0, data.size)])
    }

    private fun updateView(word: Word) {
        findViewById<TextView>(R.id.chinese).text = word.chinese
        findViewById<TextView>(R.id.pinyin).text = word.pinyin
        findViewById<TextView>(R.id.english).text = word.english
    }

    private fun setRepeatedAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 5000, ALARM_TAG, {
            showNotification()
            setRepeatedAlarm()
        }, null)
    }

    private fun showNotification() {
        val intent = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), 0)

        val notification = NotificationCompat.Builder(this, MainApplication.CHANNEL_DEFAULT)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentText(getString(R.string.notification))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(intent)
            .setAutoCancel(true)
            .build()

        val notificationManager = getSystemService(Application.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}
