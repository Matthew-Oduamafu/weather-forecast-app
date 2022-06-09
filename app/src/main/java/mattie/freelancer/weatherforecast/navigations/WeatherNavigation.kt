package mattie.freelancer.weatherforecast.navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mattie.freelancer.weatherforecast.screens.about.AboutScreen
import mattie.freelancer.weatherforecast.screens.favorites.FavoritesScreen
import mattie.freelancer.weatherforecast.screens.main.MainScreen
import mattie.freelancer.weatherforecast.screens.main.MainViewModel
import mattie.freelancer.weatherforecast.screens.search_screen.SearchScreen
import mattie.freelancer.weatherforecast.screens.settings.SettingsScreen
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

            val route = WeatherScreens.MAIN_SCREEN.name
            composable(
                "$route/{city}",
                arguments = listOf(navArgument(name = "city") { type = NavType.StringType})
            ) { navBackStackEntry ->
                navBackStackEntry.arguments?.getString("city").let {
                    val mainViewModel: MainViewModel = hiltViewModel()
                    MainScreen(navController, mainViewModel, it /*Where this it is the city to search*/)
                }
            }

            composable(WeatherScreens.SEARCH_SCREEN.name)    {
                SearchScreen(navController)
            }

            composable(WeatherScreens.ABOUT_SCREEN.name)    {
                AboutScreen(navController = navController)
            }

            composable(WeatherScreens.SETTING_SCREEN.name)    {
                SettingsScreen(navController = navController)
            }

            composable(WeatherScreens.FAVORITE_SCREEN.name)    {
                FavoritesScreen(navController = navController)
            }
        }
    )
}
