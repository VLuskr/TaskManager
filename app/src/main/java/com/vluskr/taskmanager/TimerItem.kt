package com.vluskr.taskmanager

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.lang.reflect.Modifier

data class TimerItem(
    val nameTimer: String,
    val context: Context
) {
    private var isStarted = false
    private var totalTime: Long
    private var startTime = 0L
    private var endTime = 0L

    init {
        val shared = context.getSharedPreferences(nameTimer, Context.MODE_PRIVATE)
        totalTime = shared.getLong("total", 0)
    }

    fun press(): String {
        isStarted = !isStarted
        if (isStarted) start()
        else stop()

        return infoTotal()
    }

    fun clear(): String {
        Toast.makeText(context, "Total time $nameTimer\nis Cleared", Toast.LENGTH_LONG).show()
        stop()
        totalTime = 0
        saveTotal()
        return infoTotal()
    }

    private fun start() {
        isStarted = true
        startTime = System.currentTimeMillis()
    }

    private fun stop() {
        isStarted = false
        endTime = System.currentTimeMillis()
        totalTime += (endTime - startTime)
        saveTotal()
    }

    private fun saveTotal() {
        val shared = context.getSharedPreferences(nameTimer, Context.MODE_PRIVATE)
        shared.edit().putLong("total", totalTime).apply()
    }

    fun infoTotal(): String {
        return if (isStarted) "in progress"
        else (totalTime / 1000).toString() + " sec"
    }

    fun name() = nameTimer
}


