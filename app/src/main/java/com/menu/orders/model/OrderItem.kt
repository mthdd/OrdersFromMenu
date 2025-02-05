package com.menu.orders.model

data class OrderItem(
    val id: Int,
    val title: String,
    val date: String, // Используйте LocalDate или DateUtils для форматирования
    val status: String,
    val customerName: String
)