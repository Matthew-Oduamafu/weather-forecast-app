package mattie.freelancer.weatherforecast.widgets

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mattie.freelancer.weatherforecast.model.Favorite
import mattie.freelancer.weatherforecast.navigations.WeatherScreens
import mattie.freelancer.weatherforecast.screens.favorites.FavoriteViewModel
import mattie.freelancer.weatherforecast.utils.Constants

private const val TAG = "WeatherAppBar"


@Composable
fun WeatherAppBar(
    title: String = "Seattle, US",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    val temp = title.split(",")

    val isAlreadyFavList =
        favoriteViewModel.favList.collectAsState().value.filter { it.city == temp[0].trim() }
    val context = LocalContext.current

    Log.d(TAG, "WeatherAppBar: called")
    val showDialog = remember { mutableStateOf(false) }
    val expandDropDown = remember { mutableStateOf(true) }

    if (showDialog.value) {
        ShowSettingDropDownMenu(
            showDialog = showDialog,
            navController = navController,
            expandDropDown = expandDropDown
        )
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search button icon"
                    )
                }
                IconButton(
                    onClick = {
                        Log.d(TAG, "WeatherAppBar: showDialog value is ${showDialog.value}")
                        showDialog.value = true
                        expandDropDown.value = true
                    }
                ) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More Icon")
                }
            } else Box {}
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Navigation icon",
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable { onButtonClicked.invoke() }
                )
            }
            if (isMainScreen) {
                Icon(
                    imageVector = if (isAlreadyFavList.isNotEmpty()) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = "favorite icon",
                    modifier = Modifier
                        .scale(0.9f)
                        .clickable {
                            // we first check if city is already saved as state first
                            if (isAlreadyFavList.isEmpty()) {
                                favoriteViewModel.insertFavorite(
                                    Favorite(
                                        city = temp[0].trim(),  // city name
                                        country = temp[1].trim() // country code
                                    )
                                )
                                Toast
                                    .makeText(
                                        context,
                                        "${temp[0]} added to favorite!",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        },
                    tint = Color.Red.copy(alpha = 0.6f)
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}

@Composable
fun ShowSettingDropDownMenu(
    showDialog: MutableState<Boolean>,
    navController: NavController,
    expandDropDown: MutableState<Boolean>
) {
    Log.d(TAG, "ShowSettingDropDownMenu: called")
//    var expanded by remember { mutableStateOf(true) }
//    expanded = if (showDialog.value) expandDropDown else false
    val items = listOf("About", "Favorites", "Settings")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expandDropDown.value,
            onDismissRequest = { expandDropDown.value = false },
            modifier = Modifier
                .width(140.dp)
                .background(
                    Color.White
                )
        ) {
            items.forEach { text ->
                DropdownMenuItem(
                    onClick = {
                        expandDropDown.value = false
                        showDialog.value = false
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    when (text) {
                                        "About" -> WeatherScreens.ABOUT_SCREEN.name
                                        "Settings" -> WeatherScreens.SETTING_SCREEN.name
                                        "Favorites" -> WeatherScreens.FAVORITE_SCREEN.name
                                        else -> WeatherScreens.MAIN_SCREEN.name + "/${Constants.DEFAULT_CITY}"
                                    }
                                )
                            }
                    ) {
                        Icon(
                            imageVector = when (text) {
                                "About" -> Icons.Default.Info
                                "Settings" -> Icons.Default.Settings
                                "Favorites" -> Icons.Default.FavoriteBorder
                                else -> Icons.Default.Settings
                            },
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                        Text(
                            text = text,
                            fontWeight = FontWeight.W300
                        )
                    }
                }
            }
        }
    }
}
