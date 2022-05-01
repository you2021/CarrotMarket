package com.example.carrotmarket

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.carrotmarket.join.JoinActivity
import com.example.carrotmarket.login.LoginActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    val channel = LoginActivity.PRIMARY_CHNNEL_ID

    companion object{
        var title: String? =  null
        var body : String? = null
        var check : Boolean? = false
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.d("TOKEN", p0.data.toString())
        Log.d("TOKEN", p0.notification.toString())

        sendNotification()

    }

    private fun sendNotification() {

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent =
            PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channel)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(this, channel)
            .setContentTitle("title")
            .setContentText("body")
            .setSmallIcon(R.drawable.carrot)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        manager.notify(10, notification)

    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

        Log.i("TOKEN","newToken : $p0")
    }


}