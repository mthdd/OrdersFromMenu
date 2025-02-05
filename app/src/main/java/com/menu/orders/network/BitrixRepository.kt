package com.menu.orders.network

import com.menu.orders.localdb.OrderDao
import com.menu.orders.model.OrderItem

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