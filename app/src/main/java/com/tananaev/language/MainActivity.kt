package com.tananaev.language

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var data: List<Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data = DataLoader.load(this, R.raw.hsk1)

        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.button_next).setOnClickListener {
            updateView(data[Random.nextInt(0, data.size)])
        }

        if (PendingIntent.getBroadcast(this, 0, alarmIntent(), PendingIntent.FLAG_NO_CREATE) == null) {
            setRepeatedAlarm()
        }
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

    private fun alarmIntent(): Intent {
        val intent = Intent(this.javaClass.name.replace("MainActivity", "NOTIFICATION"))
        intent.setClass(this, AlarmReceiver::class.java)
        return intent
    }

    private fun setRepeatedAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = PendingIntent.getBroadcast(this, 0, alarmIntent(), 0)
        alarmManager.setRepeating(
            AlarmManager.RTC, AlarmScheduler.nextAlarmTime(), AlarmManager.INTERVAL_HOUR, intent)
    }
}
