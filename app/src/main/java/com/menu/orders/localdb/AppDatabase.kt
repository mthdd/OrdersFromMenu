// AppDatabase.kt
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.menu.orders.Converters
import com.menu.orders.localdb.OrderDao
import com.menu.orders.model.Order
import com.menu.orders.model.OrderItemConverter

@Database(
    entities = [Order::class],
    version = 1
)
@TypeConverters(OrderItemConverter::class) // Добавьте, если не указали в классе Order
abstract class AppDatabase : RoomDatabase() {
    // ...
}