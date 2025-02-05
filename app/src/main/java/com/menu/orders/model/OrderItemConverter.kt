package com.menu.orders.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OrderItemConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromOrderItemList(items: List<OrderItem>): String {
        return gson.toJson(items)
    }

    @TypeConverter
    fun toOrderItemList(json: String): List<OrderItem> {
        val type = object : TypeToken<List<OrderItem>>() {}.type
        return gson.fromJson(json, type)
    }
}