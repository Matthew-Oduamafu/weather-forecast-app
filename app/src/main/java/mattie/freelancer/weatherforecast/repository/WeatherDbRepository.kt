package mattie.freelancer.weatherforecast.repository

import kotlinx.coroutines.flow.Flow
import mattie.freelancer.weatherforecast.data.Favorite
import mattie.freelancer.weatherforecast.data.WeatherDatabaseDao
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDatabaseDao: WeatherDatabaseDao) {
    fun getFavorites(): Flow<List<Favorite>> = weatherDatabaseDao.getFavorites()

    suspend fun insertFavorite(favorite: Favorite) = weatherDatabaseDao.insertFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = weatherDatabaseDao.updateFavorite(favorite)

    suspend fun deleteAllFavorites() = weatherDatabaseDao.deleteAllFavorites()

    suspend fun deleteFavorite(favorite: Favorite) = weatherDatabaseDao.deleteAFavorite(favorite)

    suspend fun getFavById(city: String): Favorite = weatherDatabaseDao.getFavById(city)
}