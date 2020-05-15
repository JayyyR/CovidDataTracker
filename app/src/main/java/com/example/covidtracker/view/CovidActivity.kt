package com.example.covidtracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.covidtracker.CovidApp
import com.example.covidtracker.R
import com.example.covidtracker.data.CovidDataApi
import com.example.covidtracker.data.CovidDataRepo
import com.example.covidtracker.viewmodel.CovidViewModel

class CovidActivity : AppCompatActivity() {

    private var viewModel: CovidViewModel? = null

    private val viewModelFactory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CovidViewModel(
                    covidDataApi = getCovidApp().retrofit.create(CovidDataApi::class.java),
                    covidDataDao = getCovidApp().databaseHelper.covidDb.covidDataDao()
            ) as T
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CovidViewModel::class.java)
    }

    private fun getCovidApp(): CovidApp {
        return applicationContext as CovidApp
    }
}