package mattie.freelancer.weatherforecast.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import mattie.freelancer.weatherforecast.model.Unit
import mattie.freelancer.weatherforecast.repository.WeatherDbRepository
import javax.inject.Inject

private const val TAG = "SettingsViewModel"

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: WeatherDbRepository) :
    ViewModel() {
    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        Log.d(TAG, "init: called")
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged().collect { listOfUnits ->
                if (listOfUnits.isEmpty()) {
                    Log.d(TAG, "init: empty units")
                } else {
                    _unitList.value = listOfUnits
                    Log.d(TAG, "init: unit list is ${unitList.value}")
                }
            }
        }
    }

    fun insertUnit(unit: Unit) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUnit(unit)
    }

    fun updateUnit(unit: Unit) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateUnit(unit)
    }

    fun deleteAllUnits() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllUnits()
    }

    fun deleteUnit(unit: Unit) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteUnit(unit)
    }
}