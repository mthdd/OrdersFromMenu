package com.menu.orders.model

import android.graphics.Color
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import android.os.Parcel
import android.os.Parcelable





@Entity(tableName = "orders")
@TypeConverters(OrderItemConverter::class) // Добавьте эту строку
data class Order(
    @PrimaryKey(autoGenerate = true)
    val localId: Int,
    val bitrixId: Int,
    // другие поля
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        // чтение других полей
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(localId)
        parcel.writeInt(bitrixId)
        // запись других полей
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }
}

enum class OrderType {
    IN_HOUSE, TAKEAWAY, DELIVERY
}

enum class OrderStatus(val colorResId: Int) {
    NEW(Color.GREEN),
    PREPARING(Color.YELLOW),
    READY(Color.RED),
    COMPLETED(Color.GRAY)
}

