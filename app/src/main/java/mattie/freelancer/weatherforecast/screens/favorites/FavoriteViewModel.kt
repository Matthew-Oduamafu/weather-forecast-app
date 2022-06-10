package mattie.freelancer.weatherforecast.screens.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import mattie.freelancer.weatherforecast.model.Favorite
import mattie.freelancer.weatherforecast.repository.WeatherDbRepository
import javax.inject.Inject

private const val TAG = "FavoriteViewModel"

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository) :
    ViewModel() {
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        Log.d(TAG, "init: called")
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged().collect { listOfFavs ->
                if (listOfFavs.isEmpty()) {
                    Log.d(TAG, "init: empty favorites")
                } else {
                    _favList.value = listOfFavs
                    Log.d(TAG, "init: fav list is ${favList.value}")
                }
            }
        }
    }

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertFavorite(favorite)
    }

    fun updateFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateFavorite(favorite)
    }

    fun deleteAllFavorites() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllFavorites()
    }

    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteFavorite(favorite)
    }

    fun getFavById(city: String) = viewModelScope.launch { repository.getFavById(city) }
}