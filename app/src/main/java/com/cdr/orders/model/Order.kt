package com.cdr.orders.model

import android.graphics.Color
import java.util.Date

// Order.kt
data class Order(
    val localId: String, // Сгенерированный номер (формат: день-месяц-год-номер)
    val bitrixId: Int,
    val type: OrderType,
    val status: OrderStatus,
    val totalAmount: Double,
    val items: List<OrderItem>,
    val comment: String?,
    val createdAt: Date,
    var isPaid: Boolean = false,
    var isReadyForShipping: Boolean = false
)

enum class OrderType {
    IN_HOUSE, TAKEAWAY, DELIVERY
}

enum class OrderStatus(val colorResId: Int) {
    NEW(Color.GREEN),
    PREPARING(Color.YELLOW),
    READY(Color.RED),
    COMPLETED(Color.GRAY)
}

data class OrderItem(
    val name: String,
    val quantity: Int,
    val price: Double,
    val total: Double
)