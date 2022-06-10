package mattie.freelancer.weatherforecast.screens.no_search_result

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mattie.freelancer.weatherforecast.navigations.WeatherScreens

@Composable
fun NoSearchResultScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //⁜‣⁍
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("No search result found")
                }
            })

            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append("This could be due to:")
                }
            })

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue.copy(.5f)
                        )
                    ) {
                        append("⁜ ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
                        append("Internet connection")
                    }
                })
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue.copy(.5f)
                        )
                    ) {
                        append("⁜ ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
                        append("Invalid city name")
                    }
                })
            }

            Button(onClick = { navController.navigate(WeatherScreens.SEARCH_SCREEN.name) }) {
                Text(text = "Go to search")
            }
        }
    }
}