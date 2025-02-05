package com.menu.orders.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.menu.orders.model.Order
import com.menu.orders.model.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders")
    suspend fun getAll(): List<Order>

    @Insert
    suspend fun insertAll(orders: List<Order>)
}