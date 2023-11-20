package acecard.avitotask1.navigation

import acecard.avitotask1.R
import acecard.avitotask1.ui.PinsDataViewModel
import acecard.avitotask1.ui.screens.FilterActivity
import acecard.avitotask1.ui.screens.MapActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(
    pinsDataViewModel: PinsDataViewModel,
    navController: NavHostController,
) {
    val context = LocalContext.current
    pinsDataViewModel.readJsonFromFile(
        filePath = context.resources.openRawResource(R.raw.pins)
    )
    val pinsData by pinsDataViewModel.pinsData.collectAsState()
    val pinDataIsNotNull = remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = pinsData) {
        pinsData.let {
            pinDataIsNotNull.intValue = 1
        }
    }
    NavHost(navController = navController, startDestination = Screens.MapActivity.route) {

        composable(route = Screens.MapActivity.route) {

            if (pinDataIsNotNull.intValue == 1) {
                MapActivity(pinsDataViewModel, navController, pinsData!!)
            }
        }

        composable(route = Screens.FilterActivity.route) {
            if (pinDataIsNotNull.intValue == 1) {
                FilterActivity(
                    pinData = pinsData!!,
                    pinsDataViewModel = pinsDataViewModel
                )
            }
        }
    }
}