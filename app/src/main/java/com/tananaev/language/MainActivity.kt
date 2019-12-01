package com.tananaev.language

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = DataLoader.load(this, R.raw.hsk1)

        findViewById<View>(R.id.button_next).setOnClickListener {
            updateView(data[Random.nextInt(0, data.size)])
        }

        updateView(data[Random.nextInt(0, data.size)])
    }

    private fun updateView(word: Word) {
        findViewById<TextView>(R.id.chinese).text = word.chinese
        findViewById<TextView>(R.id.pinyin).text = word.pinyin
        findViewById<TextView>(R.id.english).text = word.english
    }
}
