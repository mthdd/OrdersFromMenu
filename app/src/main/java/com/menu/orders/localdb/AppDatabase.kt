// AppDatabase.kt
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.menu.orders.Converters
import com.menu.orders.localdb.OrderDao
import com.menu.orders.model.Order

@Database(
    entities = [Order::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    // ...
}