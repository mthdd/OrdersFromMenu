package com.menu.orders.localdb// com.menu.orders.localdb.AppDatabase.kt
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
@TypeConverters(OrderItemConverter::class) // Добавьте, если не указали в классе Order
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): Any
    // ...
}