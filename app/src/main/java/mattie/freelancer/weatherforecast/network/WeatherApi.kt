package mattie.freelancer.weatherforecast.network

import mattie.freelancer.weatherforecast.model.Weather
import mattie.freelancer.weatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

// https://api.openweathermap.org/data/2.5/forecast?q=london&cnt=7&appid=3966288184c8c4c1e9766546d4649c23
// https://api.openweathermap.org/data/2.5/forecast?q=london&cnt=7&units=imperial&appid=3966288184c8c4c1e9766546d4649c23
@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast")
    suspend fun getWeather(
        @Query(value = "q") query: String,
//        @Query(value = "cnt") cnt: String = "14",
        @Query(value = "units") units: String = "imperial",
        @Query(value = "appid") appid: String = Constants.API_KEY
    ): Weather
}
