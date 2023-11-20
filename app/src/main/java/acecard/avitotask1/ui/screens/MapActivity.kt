package acecard.avitotask1.ui.screens

import acecard.avitotask1.model.PinsData
import acecard.avitotask1.navigation.Screens
import acecard.avitotask1.ui.PinsDataViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapActivity(
    pinsDataViewModel: PinsDataViewModel,
    navController: NavHostController,
    pinsData: PinsData,
) {

    val randomPin = pinsData.pins.random().coordinates
    val randomCoordinates = LatLng(randomPin.lat, randomPin.lng)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(randomCoordinates, 10f)
    }
    val listOfSelectedServices by pinsDataViewModel.listOfSelectedServices.collectAsState()

    Box(Modifier.fillMaxSize()) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {

            listOfSelectedServices.forEach { listOfSelectedServices->
                pinsData.pins.forEach {
                    it.takeIf { it.service == listOfSelectedServices }?.apply {
                        val pin = LatLng(it.coordinates.lat, it.coordinates.lng)
                        val pinState = MarkerState(pin)
                        Marker(state = pinState, title = it.service)
                    }
                }
            }
        }
        Button(onClick = { navController.navigate(Screens.FilterActivity.route) }) {
            Text(text = "Filter")
        }
    }
}