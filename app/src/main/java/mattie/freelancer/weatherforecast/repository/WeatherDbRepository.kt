package mattie.freelancer.weatherforecast.repository

import kotlinx.coroutines.flow.Flow
import mattie.freelancer.weatherforecast.data.WeatherDatabaseDao
import mattie.freelancer.weatherforecast.model.Favorite
import mattie.freelancer.weatherforecast.model.Unit
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDatabaseDao: WeatherDatabaseDao) {
    fun getFavorites(): Flow<List<Favorite>> = weatherDatabaseDao.getFavorites()

    suspend fun insertFavorite(favorite: Favorite) = weatherDatabaseDao.insertFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = weatherDatabaseDao.updateFavorite(favorite)

    suspend fun deleteAllFavorites() = weatherDatabaseDao.deleteAllFavorites()

    suspend fun deleteFavorite(favorite: Favorite) = weatherDatabaseDao.deleteFavorite(favorite)

    suspend fun getFavById(city: String): Favorite = weatherDatabaseDao.getFavById(city)


    // repository for Unit table
    fun getUnits(): Flow<List<Unit>> = weatherDatabaseDao.getUnits()

    suspend fun insertUnit(unit: Unit) = weatherDatabaseDao.insertUnit(unit)

    suspend fun updateUnit(unit: Unit) = weatherDatabaseDao.updateUnit(unit)

    suspend fun deleteAllUnits() = weatherDatabaseDao.deleteAllUnits()

    suspend fun deleteUnit(unit: Unit) = weatherDatabaseDao.deleteUnit(unit)

}