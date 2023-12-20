package com.jassikaa.themeteorlandingapp


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ResultsViewModel: ViewModel() {

    private val _meteorData = MutableLiveData<List<APIFormat>>()
    val meteorData: MutableLiveData<List<APIFormat>> get() = _meteorData
    val year = MutableLiveData<String>("")
    val count = MutableLiveData<Int>()
    var name = MutableLiveData<String>("")
    var mass = MutableLiveData<String>("")
    var lat = MutableLiveData<String>("")
    var long = MutableLiveData<String>("")
    val data = MutableLiveData<List<APIFormat>>()

    init {
        year.value = ""
        println("viewModel init")
    }
    override fun onCleared() {
        println("viewModel onCleared")
        super.onCleared()
    }

    private suspend fun getMeteorDataFromCoroutine(year: String):List<APIFormat>? {
        //using async here because we want it to return something
        val defer = CoroutineScope(Dispatchers.IO).async {
            val url = URL("https://data.nasa.gov/resource/gh4g-9sfh.json?year=$year-01-01T00:00:00.000")
            val connection = url.openConnection() as HttpsURLConnection
            if(connection.responseCode == 200)
            {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val request = Gson().fromJson(inputStreamReader, Array<APIFormat>::class.java).toList()
                inputStreamReader.close()
                inputSystem.close()
                return@async request
            }
            else {
                return@async null
            }
        }
        return defer.await()
    }


    fun onButtonGo(search: String) {
        year.value = search
        CoroutineScope(Dispatchers.Main).launch {
            val request = getMeteorDataFromCoroutine(search)
            if (request != null){
                Log.d("TAG", "inside the  if in onButtonGo")
                count.value = request.size
                data.value = request
                name.value = "${request[0].name}"
                mass.value = "${request[0].mass}"
                //year.value = "${request[0].year}"
                lat.value = "${request[0].reclat}"
                long.value = "${request[0].reclong}"
            }
            else {
                Log.d("TAG", "inside the else in onButtonGo")
            }

        }

    }

    fun updateInfo(year: String){
        onButtonGo(year)
    }
}

