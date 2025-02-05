package com.menu.orders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.menu.orders.databinding.ActivityMainBinding
import com.menu.orders.model.Person
import com.menu.orders.model.PersonListener
import com.menu.orders.model.PersonService

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: OrderAdapter
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Настройка RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = OrderAdapter(emptyList())
        recyclerView.adapter = adapter

        // Наблюдение за данными из ViewModel
        viewModel.orders.observe(this) { orders ->
            adapter.updateData(orders)
        }
    }
}