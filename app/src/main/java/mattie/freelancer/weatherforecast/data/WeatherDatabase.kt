package mattie.freelancer.weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDatabaseDao
}