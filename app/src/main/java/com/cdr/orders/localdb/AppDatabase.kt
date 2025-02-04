package com.cdr.orders.localdb

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.cdr.orders.model.Order
import kotlinx.coroutines.flow.Flow

// AppDatabase.kt
@Database(entities = [Order::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
}

@Dao
interface OrderDao {
    @Query("SELECT * FROM Order")
    fun getAll(): Flow<List<Order>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(orders: List<Order>)
}