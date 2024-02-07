package nl.schiphol.schipholkmm.android.flight

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun FlightScreen(
    flightRouteViewModel: FlightRouteViewModel = viewModel()
) {
    val state by flightRouteViewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = { flightRouteViewModel.loadFlightRoute() }) {
                Text(text = "Fetch Flight")
            }

            Crossfade(
                targetState = state,
                label = "",
                modifier = Modifier.weight(1f)
            ) {
                when (it) {
                    FlightState.Initial -> Text("Press the button")
                    FlightState.Loading -> Text("Loading....")
                    is FlightState.Error -> Text(text = "ERROR ${it.throwable}")
                    is FlightState.Content -> Text("$it")
                }
            }
        }
    }
}
