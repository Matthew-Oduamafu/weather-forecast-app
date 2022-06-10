package mattie.freelancer.weatherforecast.screens.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mattie.freelancer.weatherforecast.data.DataOrException
import mattie.freelancer.weatherforecast.model.Weather
import mattie.freelancer.weatherforecast.repository.WeatherRepository
import javax.inject.Inject


private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    suspend fun getWeatherData(
        city: String,
        units: String
    ): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city, units = units)
    }
}