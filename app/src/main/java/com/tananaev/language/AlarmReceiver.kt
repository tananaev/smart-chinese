package com.tananaev.language

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_ID = 1
    }

    override fun onReceive(context: Context, intent: Intent) {

        if (AlarmScheduler.checkTime()) {
            showNotification(context)
        }
    }

    private fun showNotification(context: Context) {

        val intent = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0)

        val notification = NotificationCompat.Builder(context, MainApplication.CHANNEL_DEFAULT)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentText(context.getString(R.string.notification))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(intent)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Application.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}
