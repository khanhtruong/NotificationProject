package com.truongkhanh.notificationproject

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var notificationServiceManager: NotificationServiceManager
    private lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNotification()
        handleClick()
    }

    private fun setupNotification() {
        notificationServiceManager = NotificationServiceManager()
        notificationServiceManager.createNotificationChannels(this)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private fun handleClick() {
        btnSimpleNotification.setOnClickListener {
            notificationServiceManager.buildSimpleNotification(this, notificationManager)
        }
        btnMessageNotification.setOnClickListener {
            notificationServiceManager.buildBigPictureNotification(this, notificationManager)
        }
    }
}
