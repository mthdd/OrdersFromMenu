package com.cdr.orders.network

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