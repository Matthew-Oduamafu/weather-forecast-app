package mattie.freelancer.weatherforecast.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mattie.freelancer.weatherforecast.model.Favorite
import mattie.freelancer.weatherforecast.model.Unit


@Dao
interface WeatherDatabaseDao {
    @Query(
        """
        SELECT
            *
        FROM
            fav_tbl
    """
    )
    fun getFavorites(): Flow<List<Favorite>>

    @Query(
        """
        SELECT
            *
        FROM
            fav_tbl
        WHERE
            city =:city
    """
    )
    suspend fun getFavById(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query(
        """
        DELETE FROM fav_tbl
    """
    )
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    // Dao for the unit table
    @Query(
        """
        SELECT
            *
        FROM
            settings_tbl
    """
    )
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query(
        """
        DELETE FROM settings_tbl
    """
    )
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unit: Unit)
}