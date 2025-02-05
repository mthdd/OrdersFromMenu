package com.menu.orders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.menu.orders.model.Order

class OrderViewModel : ViewModel() {
    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> get() = _orders

    fun loadOrders() {
        // Загрузите данные (например, из базы данных или API)
        val orders = listOf(Order(1, "Order 1"), Order(2, "Order 2"))
        _orders.value = orders
    }
}