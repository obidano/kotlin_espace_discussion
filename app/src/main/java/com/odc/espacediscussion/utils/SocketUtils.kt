package com.odc.espacediscussion.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.odc.espacediscussion.R
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URI
import javax.inject.Inject

class SocketUtils @Inject constructor(val context: Context) {
    var socket: Socket? = null
    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIF_ID = 0

    fun startConnection(){
        val uri: URI = URI.create(Constantes.BASE_URL)
        val options = IO.Options.builder()//.setTransports(arrayOf("websocket"))
            .build()

        try {
            socket = IO.socket(uri, options)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        socket?.connect()
    }

    @SuppressLint("MissingPermission")
    fun showNotifs(msg:String) {


        val notif = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Notification")
            .setContentText(msg)
            .setSmallIcon(R.drawable.icon_orange)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()


        val notifManger = NotificationManagerCompat.from(context)
        notifManger.notify(NOTIF_ID, notif)
    }
}