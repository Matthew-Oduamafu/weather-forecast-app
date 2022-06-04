package mattie.freelancer.weatherforecast.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mattie.freelancer.weatherforecast.screens.WeatherSplashScreen

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
        }
    )
}
