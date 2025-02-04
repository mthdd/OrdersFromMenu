package com.cdr.orders.model

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = false)
    val bitrixId: Int,
    val localId: String,
    val type: OrderType,
    val status: OrderStatus,
    val totalAmount: Double,
    val items: List<OrderItem>,
    val comment: String?,
    val createdAt: Long
) {
    // Для Room требуется пустой конструктор
    constructor() : this(0, "", OrderType.IN_HOUSE, OrderStatus.NEW, 0.0, emptyList(), null, 0L)
}

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