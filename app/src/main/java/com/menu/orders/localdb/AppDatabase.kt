package com.menu.orders.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.menu.orders.model.Order
import com.menu.orders.model.OrderItemConverter

@Database(
    entities = [Order::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(OrderItemConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
    // ...
}