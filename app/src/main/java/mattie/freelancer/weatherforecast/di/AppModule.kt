package mattie.freelancer.weatherforecast.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mattie.freelancer.weatherforecast.data.WeatherDatabase
import mattie.freelancer.weatherforecast.data.WeatherDatabaseDao
import mattie.freelancer.weatherforecast.network.WeatherApi
import mattie.freelancer.weatherforecast.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideOpenWeatherApi(): WeatherApi {
        return with(Retrofit.Builder()) {
            baseUrl(Constants.BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            build().create(WeatherApi::class.java)
        }
    }

    @Singleton
    @Provides
    fun provideWeatherDaoApi(weatherDatabase: WeatherDatabase): WeatherDatabaseDao =
        weatherDatabase.weatherDao()


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase = with(
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        )
    ) {
        fallbackToDestructiveMigration()
        build()
    }
}