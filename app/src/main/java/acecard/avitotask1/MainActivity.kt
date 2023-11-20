package acecard.avitotask1

import acecard.avitotask1.navigation.NavigationGraph
import acecard.avitotask1.ui.PinsDataViewModel
import acecard.avitotask1.ui.theme.AvitoTask1Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val pinsDataViewModel by viewModels<PinsDataViewModel>()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvitoTask1Theme {
                navController = rememberNavController()
                NavigationGraph(pinsDataViewModel, navController)
            }
        }
    }
}
