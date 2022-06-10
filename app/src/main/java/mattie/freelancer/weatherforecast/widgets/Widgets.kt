package mattie.freelancer.weatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import mattie.freelancer.weatherforecast.R
import mattie.freelancer.weatherforecast.model.City
import mattie.freelancer.weatherforecast.model.WeatherItem
import mattie.freelancer.weatherforecast.utils.formatDate
import mattie.freelancer.weatherforecast.utils.formatDateTime
import mattie.freelancer.weatherforecast.utils.formatDecimals


@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val temp = formatDateTime(weather.dt).split(":")

            Text(text = buildAnnotatedString {
                withStyle(SpanStyle()) {
                    append(formatDate(weather.dt).split(",")[0])
                }
                withStyle(SpanStyle(fontWeight = FontWeight.ExtraLight, fontSize = 12.sp)) {
                    append(", ${temp[0]}${temp.last()}")
                }
            }, modifier = Modifier.padding(start = 5.dp))


            WeatherStateImage(imageUrl = imageUrl)

            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue.copy(alpha = 0.7f),
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(formatDecimals(weather.main.temp_max) + "º")
                    }
                    withStyle(style = SpanStyle(color = Color.LightGray)) {
                        append(formatDecimals(weather.main.temp_min) + "º")
                    }
                }
            )
        }
    }
}


@Composable
fun SunsetSunriseRow(cityInfo: City) {
    Row(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise icon",
                modifier = Modifier.size(25.dp)
            )
            Text(text = formatDateTime(cityInfo.sunrise), style = MaterialTheme.typography.caption)
        }

        Row {
            Text(text = formatDateTime(cityInfo.sunset), style = MaterialTheme.typography.caption)
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset icon",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun HumidityWindPressureRow(weatherItem: WeatherItem, isImperial: Boolean) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity icon",
                modifier = Modifier.size(20.dp)
            )
            Text(text = " ${weatherItem.main.humidity}%", style = MaterialTheme.typography.caption)
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " ${weatherItem.main.pressure}psi",
                style = MaterialTheme.typography.caption
            )
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "humidity icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " ${weatherItem.wind.speed}${if (isImperial) "mph" else "m/s"}",
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = "weather state image",
        modifier = Modifier.size(80.dp)
    )
}
