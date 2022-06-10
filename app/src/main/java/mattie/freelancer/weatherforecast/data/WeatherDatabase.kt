package mattie.freelancer.weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import mattie.freelancer.weatherforecast.model.Favorite
import mattie.freelancer.weatherforecast.model.Unit


@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDatabaseDao
}