package com.truongkhanh.notificationproject

import androidx.core.app.NotificationCompat
import android.content.Context
import android.graphics.BitmapFactory
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationManagerCompat

class NotificationServiceManager {

    companion object {
        const val BIG_PICTURE_NOTIFICATION_CHANEL_ID = "bigPictureNotificationChanelID"
        const val BIG_PICTURE_NOTIFICATION_ID = 0

        const val SIMPLE_NOTIFICATION_CHANEL_ID = "simpleNotificationChanelID"
        const val SIMPLE_NOTIFICATION_ID = 1
    }

    private fun getNotificationChanel(
        chanelId: String,
        chanelName: String,
        chanelDescription: String,
        chanelImportance: Int
    ): NotificationChannel? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(chanelId, chanelName, chanelImportance)
            channel.description = chanelDescription
            return channel
        }
        return null
    }

    fun createNotificationChannels(context: Context) {
        val listNotificationChanel: ArrayList<NotificationChannel> = arrayListOf()
        getNotificationChanel(BIG_PICTURE_NOTIFICATION_CHANEL_ID,
            "Big picture notification",
            "Notification about big picture",
            NotificationManagerCompat.IMPORTANCE_DEFAULT)?.let{
            listNotificationChanel.add(it)
        }

        getNotificationChanel(SIMPLE_NOTIFICATION_CHANEL_ID,
            "Simple notification",
            "Information notification",
            NotificationManagerCompat.IMPORTANCE_DEFAULT)?.let{
            listNotificationChanel.add(it)
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannels(listNotificationChanel)
        }
    }

    fun buildBigPictureNotification(context: Context, notificationManager: NotificationManager) {
        val image = BitmapFactory.decodeResource(context.resources, R.drawable.nature)
        val style = NotificationCompat.BigPictureStyle().bigPicture(image)
        val notification = NotificationCompat.Builder(context, BIG_PICTURE_NOTIFICATION_CHANEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Big picture notification")
            .setContentText("A picture about nature water-fall")
            .setStyle(style)
        .build()
        notificationManager.notify(BIG_PICTURE_NOTIFICATION_ID, notification)
    }

    fun buildSimpleNotification(context: Context, notificationManager: NotificationManager) {
        val notification = NotificationCompat.Builder(context, SIMPLE_NOTIFICATION_CHANEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Simple notification")
            .setContentText("Information notification")
            .build()
        notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification)
    }
}