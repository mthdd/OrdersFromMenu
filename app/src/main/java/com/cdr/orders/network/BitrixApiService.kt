package com.cdr.orders.network

import com.cdr.orders.model.Order
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// BitrixApiService.kt
interface BitrixApiService {
    @GET("orders")
    suspend fun getOrders(): Response<List<Order>>

    @POST("orders/{id}/status")
    suspend fun updateStatus(
        @Path("id") bitrixId: Int,
        @Body statusUpdate: StatusUpdate
    ): Response<Unit>
}

data class StatusUpdate(
    val newStatus: String,
    val comment: String?
)