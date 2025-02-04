package com.cdr.orders.network

import com.cdr.orders.localdb.OrderDao
import com.cdr.orders.model.OrderItem

class BitrixRepository(
    private val apiService: BitrixApiService,
    private val orderDao: OrderDao
) {
    // Пример: кэширование данных из API в локальную БД
    suspend fun fetchOrders(): List<OrderItem> {
        val remoteOrders = apiService.getOrders()
        orderDao.insertAll(remoteOrders)
        return remoteOrders
    }
}