package com.menu.orders

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.menu.orders.model.Order
import com.menu.orders.model.OrderStatus

// OrderDetailActivity.kt
class OrderDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        val order = intent.getParcelableExtra<Order>("ORDER")!!

        // Заполнение данных
        findViewById<TextView>(R.id.tvLocalId).text = order.localId.toString()
        findViewById<TextView>(R.id.tvBitrixId).text = order.bitrixId.toString()
        // ... заполнение остальных полей

        setupButtons(order)
    }

    private fun setupButtons(order: Order) {
        findViewById<Button>(R.id.btnChangeStatus).setOnClickListener {
            showStatusDialog(order)
        }
        // Обработчики других кнопок
    }

    private fun showStatusDialog(order: Order) {
        val statuses = OrderStatus.values()
        val items = statuses.map { it.name }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle("Изменить статус")
            .setItems(items) { _, which ->
                updateOrderStatus(order, statuses[which])
            }
            .show()
    }

    private fun updateOrderStatus(order: Order, newStatus: OrderStatus) {
        // Обновление в Bitrix и локально
    }
}