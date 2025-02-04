package com.cdr.orders.localdb

import com.cdr.orders.model.Order
import com.cdr.orders.network.BitrixApiService
import kotlinx.coroutines.flow.Flow
import java.io.IOException

// OrderRepository.kt
class OrderRepository(
    private val api: BitrixApiService,
    private val db: AppDatabase
) {
    suspend fun refreshOrders() {
        try {
            val orders = api.getOrders()
            db.orderDao().insertAll(orders)
        } catch (e: IOException) {
            // Обработка ошибки сети
        }
    }

    fun getOrdersFlow(): Flow<List<Order>> = db.orderDao().getAll()
}