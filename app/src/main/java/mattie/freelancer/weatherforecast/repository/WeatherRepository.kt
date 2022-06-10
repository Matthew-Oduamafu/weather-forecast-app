package mattie.freelancer.weatherforecast.repository

import android.util.Log
import mattie.freelancer.weatherforecast.data.DataOrException
import mattie.freelancer.weatherforecast.model.Weather
import mattie.freelancer.weatherforecast.network.WeatherApi
import javax.inject.Inject


private const val TAG = "WeatherRepository"

class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(
        cityQuery: String,
        units: String
    ): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery, units = units)
        } catch (e: Exception) {
            Log.d(TAG, "getWeather: error occurred with message $e")
            return DataOrException(e = e)
        }

        response.also {
            Log.d(TAG, "getWeather: data fetched successfully")
            Log.d(TAG, "getWeather: data is $response")
            return DataOrException(data = it)
        }
//        return DataOrException(data = response)
        // alternative way is done above. It helps perform other things like logging
    }
}