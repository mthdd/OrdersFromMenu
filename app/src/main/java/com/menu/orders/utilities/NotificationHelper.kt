package com.menu.orders.utilities

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.menu.orders.model.OrderItem
import com.menu.orders.R

class NotificationHelper(private val context: Context) {
    fun showNotification(order: OrderItem) {
        val notificationBuilder = NotificationCompat.Builder(context, "order_channel")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("New Order: ${order.title}")
            .setContentText("Status: ${order.status}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(order.id, notificationBuilder.build())
    }
}