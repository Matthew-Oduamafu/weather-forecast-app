package mattie.freelancer.weatherforecast.navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mattie.freelancer.weatherforecast.screens.main.MainScreen
import mattie.freelancer.weatherforecast.screens.main.MainViewModel
import mattie.freelancer.weatherforecast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SPLASH_SCREEN.name,
        builder = {
            composable(WeatherScreens.SPLASH_SCREEN.name) {
                WeatherSplashScreen(navController)
            }

            composable(WeatherScreens.MAIN_SCREEN.name) {
                val mainViewModel:MainViewModel = hiltViewModel()
                MainScreen(navController, mainViewModel)
            }
        }
    )
}
