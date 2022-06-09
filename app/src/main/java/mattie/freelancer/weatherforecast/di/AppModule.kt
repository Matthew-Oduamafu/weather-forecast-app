package mattie.freelancer.weatherforecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}