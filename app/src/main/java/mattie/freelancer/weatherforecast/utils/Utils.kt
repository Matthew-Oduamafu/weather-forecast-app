package mattie.freelancer.weatherforecast.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(timestamp: Int): String {
    SimpleDateFormat("EEE, MMM d").format(Date(timestamp.toLong() * 1000)).also { return it }
}

fun formatDateTime(timestamp: Int): String {
    SimpleDateFormat("hh:mm:aa").format(Date(timestamp.toLong() * 1000)).also { return it }
}

fun formatDecimals(number: Double): String {
    return "%.0f".format(number)
}