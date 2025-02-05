package com.menu.orders.network

import com.menu.orders.localdb.OrderDao
import com.menu.orders.model.OrderItem

class BitrixRepository(
    private val apiService: BitrixApiService,
    private val orderDao: OrderDao
) {
    // Пример: кэширование данных из API в локальную БД
    suspend fun fetchOrders(): List<OrderItem> {
        val response = apiService.getOrders()
        if (response.isSuccessful) {
            val remoteOrders = response.body() ?: emptyList()
            // Преобразуем List<Order> в List<OrderItem>
            val orderItems = remoteOrders.map { order ->
                OrderItem(
                    // Пример преобразования полей:
                    id = order.localId,
                    title = order.toString()
                )
            }
            orderDao.insertAll(orderItems)
            return orderItems
        } else {
            // Обработка ошибки, например, возврат пустого списка или выброс исключения
            return emptyList()
        }
    }
}