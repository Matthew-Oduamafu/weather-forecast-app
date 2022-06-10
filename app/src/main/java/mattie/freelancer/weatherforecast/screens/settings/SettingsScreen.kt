package mattie.freelancer.weatherforecast.screens.settings

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import mattie.freelancer.weatherforecast.model.Unit
import mattie.freelancer.weatherforecast.widgets.WeatherAppBar

private const val TAG = "SettingsScreen"

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    var unitToggleState by remember { mutableStateOf(false) }
    val measurementUnits = listOf("Imperial (F)", "Metric (C)")
    val choiceFromDb = settingsViewModel.unitList.collectAsState().value

    val defaultChoice = if (choiceFromDb.isEmpty()) measurementUnits[0] else choiceFromDb[0].unit
    unitToggleState = defaultChoice == measurementUnits[0]
    var choiceState by remember { mutableStateOf(defaultChoice) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Settings",
                icon = Icons.Rounded.ArrowBack,
                isMainScreen = false,
                navController = navController,
                onButtonClicked = { navController.popBackStack() }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Change Unit of Measurement",
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                IconToggleButton(
                    checked = !unitToggleState,
                    onCheckedChange = {
                        unitToggleState = !it
                        choiceState = if (unitToggleState) "Imperial (F)" else "Metric (C)"
                    },
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .background(color = Color.Magenta.copy(.45f))
                ) {
                    Log.d(TAG, "SettingsScreen: choice is $choiceState")
                    Text(text = if (unitToggleState) "Fahrenheit ºF" else "Celsius ºC")
                }

                Button(
                    onClick = {
                        settingsViewModel.also { it.deleteAllUnits();it.insertUnit(Unit(unit = choiceState)) }
                        Toast.makeText(context, "$choiceState saved!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.padding(3.dp),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEFBE42))
                ) {
                    Text(
                        text = "Save",
                        modifier = Modifier.padding(4.dp),
                        color = Color.White,
                        fontSize = 17.sp
                    )
                }
            }

        }
    }
}