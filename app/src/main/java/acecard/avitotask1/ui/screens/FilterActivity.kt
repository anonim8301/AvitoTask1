package acecard.avitotask1.ui.screens

import acecard.avitotask1.model.PinsData
import acecard.avitotask1.ui.PinsDataViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FilterActivity(
    pinData: PinsData,
    pinsDataViewModel: PinsDataViewModel,
) {
    val listOfSelectedServices by pinsDataViewModel.listOfSelectedServices.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select which services you want to be showed.",
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )
        pinData.services.forEach { service ->

            val isChecked = remember { mutableStateOf(listOfSelectedServices.contains(service)) }

            Row(modifier = Modifier.padding(8.dp)) {

                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = !isChecked.value
                        if (isChecked.value) {
                           pinsDataViewModel.addToList(service)
                        } else {
                            pinsDataViewModel.removeFromList(service)
                        }
                    },
                    enabled = true,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Magenta,
                        uncheckedColor = Color.DarkGray,
                        checkmarkColor = Color.Cyan
                    )
                )
                Text(text = service)
            }
        }
    }
}
