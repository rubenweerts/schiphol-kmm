package nl.schiphol.schipholkmm.android.flight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import at.asitplus.KmmResult
import kotlinx.collections.immutable.toImmutableList
import nl.schiphol.schipholkmm.common.CommonDefaults
import nl.schiphol.schipholkmm.data.model.flight.PoCFlight

@Composable
fun FlightScreen(
    flightRouteViewModel: FlightRouteViewModel = viewModel()
) {
    val flights by flightRouteViewModel.observeFlights().collectAsState(initial = emptyMap())

    var flightIdentifier by remember { mutableStateOf(CommonDefaults.defaultFlightId()) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = flightIdentifier,
                    onValueChange = { flightIdentifier = it }
                )
                Button(
                    onClick = {
                        flightRouteViewModel.addFlight(flightIdentifier)
                        flightIdentifier = CommonDefaults.defaultFlightId()
                    }
                ) {
                    Text(text = "Add flight")
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(flights.entries.toImmutableList()) { resultEntry ->
                    FlightCard(
                        flightId = resultEntry.key,
                        flightResult = resultEntry.value,
                        onRemoveFlightClicked = { flightRouteViewModel.removeFlight(it) },
                    )
                }
            }
        }
    }
}


@Composable
fun FlightCard(
    modifier: Modifier = Modifier,
    flightId: String,
    flightResult: KmmResult<PoCFlight>,
    onRemoveFlightClicked: (String) -> Unit,
) {
    val flight = flightResult.getOrElse {
        PoCFlight(flightId, null, null, null, null)
    }
    val cardColors = if (flightResult.isFailure) {
        CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    } else CardDefaults.cardColors()

    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = cardColors
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                if(flightResult.isSuccess) {
                    Text(text = "Flight Number = ${flight.id}")
                    Text(text = "Departure = ${flight.departureDateTime}")
                    Text(text = "Departing Airport = ${flight.departureAirport}")
                    Text(text = "Arrival = ${flight.arrivalDateTime}")
                    Text(text = "Arrival Airport = ${flight.arrivalAirport}")
                }else{
                    Text(text = "${flightResult.exceptionOrNull()?.message}")
                }
            }

            Column(
                modifier = Modifier.width(36.dp)
            ) {
                IconButton(onClick = { onRemoveFlightClicked(flightId) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }
        }
    }
}
