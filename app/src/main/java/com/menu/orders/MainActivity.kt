package com.menu.orders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.menu.orders.adapter.OrderAdapter
import com.menu.orders.viewmodel.OrderViewModel
import androidx.activity.viewModels
import com.menu.orders.model.Order

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: OrderAdapter // Исправлено на OrderAdapter
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Настройка RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = OrderAdapter(emptyList<Order>()) // Исправлено на Order
        recyclerView.adapter = adapter

        // Наблюдение за данными из ViewModel
        viewModel.orders.observe(this) { orders ->
            val currentAdapter = adapter // Локальная переменная
            currentAdapter.updateData(orders)
        }
    }
}