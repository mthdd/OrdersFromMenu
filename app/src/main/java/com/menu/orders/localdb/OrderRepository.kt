package com.menu.orders.localdb

import com.menu.orders.model.Order
import com.menu.orders.network.BitrixApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.io.IOException

class OrderRepository(
    private val api: BitrixApiService,
    private val db: AppDatabase
) {
    suspend fun refreshOrders() {
        try {
            val response: Response<List<Order>> = api.getOrders()
            if (response.isSuccessful) { // Проверяем, успешен ли ответ
                val orders = response.body() // Извлекаем тело ответа
                if (orders != null) { // Проверяем, что тело ответа не null
                    db.orderDao().insertAll(orders) // Передаем List<Order> в DAO
                }
            } else {
                // Обработка ошибки, если ответ не успешен
                println("Ошибка при получении заказов: ${response.code()} ${response.message()}")
            }
        } catch (e: IOException) {
            // Обработка ошибки сети
            println("Сетевая ошибка: ${e.message}")
        }
    }

    fun getOrdersFlow(): Flow<List<Order>> = db.orderDao().getAll()
}