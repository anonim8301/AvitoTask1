package acecard.avitotask1.ui

import acecard.avitotask1.model.PinsData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class PinsDataViewModel @Inject constructor() : ViewModel() {

    private val _pinsData: MutableStateFlow<PinsData?> = MutableStateFlow(null)
    val pinsData: StateFlow<PinsData?> = _pinsData

    private val _listOfSelectedServices =
        MutableStateFlow(listOf<String>())
    val listOfSelectedServices: StateFlow<List<String>> = _listOfSelectedServices

    private var isJsonRead = false

    fun readJsonFromFile(filePath: InputStream) {
        if (!isJsonRead) {
            try {
                val inputStream: InputStream = filePath
                val json = inputStream.bufferedReader().use { it.readText() }
                val data = Gson().fromJson(json, PinsData::class.java)
                _pinsData.value = data

                _listOfSelectedServices.value = data.services

            } catch (e: Exception) {
                e.printStackTrace()
            }
            isJsonRead = true
        }
    }

    fun addToList(service: String) {
        val currentList = _listOfSelectedServices.value.toMutableList()
        if (!currentList.contains(service)) {
            currentList.add(service)
            _listOfSelectedServices.value = currentList
        }
    }

    fun removeFromList(service: String) {
        val currentList = _listOfSelectedServices.value.toMutableList()
        if (currentList.contains(service)) {
            currentList.remove(service)
            _listOfSelectedServices.value = currentList
        }
    }

}


