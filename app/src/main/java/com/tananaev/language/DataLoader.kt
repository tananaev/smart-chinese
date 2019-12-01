package com.tananaev.language

import android.content.Context
import androidx.annotation.RawRes
import java.io.BufferedReader
import java.io.InputStreamReader

object DataLoader {

    fun load(context: Context, @RawRes fileId: Int): List<Word> {
        val result = mutableListOf<Word>()
        val reader = BufferedReader(InputStreamReader(context.resources.openRawResource(fileId)))
        while (reader.ready()) {
            val values = reader.readLine().split(";")
            result.add(Word(values[0], values[1], values[2]))
        }
        return result
    }
}
