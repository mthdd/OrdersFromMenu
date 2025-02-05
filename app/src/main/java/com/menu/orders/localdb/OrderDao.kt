package com.menu.orders.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.menu.orders.model.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(orders: kotlin.collections.List<com.menu.orders.model.Order>) // Изменено: принимает List<Order>

    @Query("SELECT * FROM orders")
    fun getAll(): Flow<List<Order>>
}