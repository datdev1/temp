package com.b21dccn216.smarthome.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.b21dccn216.smarthome.R


@Composable
fun AdviceBox(
    advice: String,
    icon: Int
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = Color.White,
        shape = RoundedCornerShape(25),
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .weight(1f),
                painter = painterResource(id = icon),
                contentDescription = null)
            VerticalDivider(modifier = Modifier
                .height(96.dp)
                .padding(horizontal = 8.dp))
            Text(
                text = advice,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(9f)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun CombinedSensorAdviceUIPreview() {
    Column {
        AdviceBox(advice = getLightAdvice(350), icon = R.drawable.brightness)
        AdviceBox(advice = getCombinedAdvice(31,71), icon = R.drawable.cloudy)

    }
}

fun getLightAdvice(lux: Int ): String {
    return when (lux){
        in 0..500 -> "The light may be harmful to your eyes.\nAvoid reading or using a smartphone.\nIncrease light to avoid eye strain."
        in 500..1000 -> "The light is perfect.\nEnjoy it!!"
        in 1000..2500 -> "The light is too bright for reading or using a smartphone.\nAvoid reading or using a smartphone.\nDim the lights to avoid glare."
        else -> "Light level may be harmful for your eyes and cause eyes strain and glare.\nDim the lights to avoid glare."
    }
}


fun getCombinedAdvice(temperature: Int, humidity: Int): String {
    return when {
        temperature > 30 && humidity > 70 -> "It’s hot and humid.\nStay indoors, drink plenty of water, and use a fan or AC."
        temperature > 30 && humidity < 30 -> "It’s hot and dry. \nStay hydrated and use a humidifier if possible."
        temperature < 15 && humidity > 70 -> "It’s cold and humid. \nUse a heater and dehumidifier to avoid dampness."
        temperature < 15 && humidity < 30 -> "It’s cold and dry.\nWear warm clothes and consider using a humidifier."
        temperature in 20..25 && humidity in 30..60 -> "The temperature and humidity are at comfortable levels."
        temperature > 30 -> "It’s hot.\nStay cool and hydrated."
        temperature < 15 -> "It’s cold.\nWear warm clothes or turn on the heater."
        humidity > 70 -> "High humidity detected.\nConsider using a dehumidifier or ensuring ventilation."
        humidity < 30 -> "Low humidity detected.\nConsider using a humidifier."
        else -> "Everything seems fine!"
    }
}
