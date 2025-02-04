package com.cdr.orders.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cdr.orders.model.Order
import com.cdr.orders.model.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders")
    suspend fun getAll(): List<Order>

    @Insert
    suspend fun insertAll(orders: List<Order>)
}