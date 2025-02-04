// AppDatabase.kt
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cdr.orders.Converters
import com.cdr.orders.localdb.OrderDao
import com.cdr.orders.model.Order

@Database(
    entities = [Order::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    // ...
}