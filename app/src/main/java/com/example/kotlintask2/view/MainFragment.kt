package com.example.kotlintask2.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.kotlintask2.R
import com.example.kotlintask2.databinding.FragmentMainBinding
import com.example.kotlintask2.model.Weather
import com.example.kotlintask2.viewmodel.AppState
import com.example.kotlintask2.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val observer = Observer<AppState>{
            renderData(it);
        }
        viewModel.getLiveData().observe(this,observer)
        viewModel.getWeather()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun renderData(appState : AppState) {
        when (appState) {
            is AppState.Success -> {
                val weatherData = appState.weatherData

                binding.loadingLayout.visibility = View.GONE

                Snackbar.make(binding.mainView, "Success", Snackbar.LENGTH_LONG).show()
                setData(weatherData)
            }

            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE

                Snackbar.make(binding.mainView, "Error", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.reload)) {
                        viewModel.getWeather()
                    }
                    .show()
            }
        }
    }

    private fun setData(weatherData : Weather) {
        binding.cityName.text = weatherData.city.city

        binding.cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            weatherData.city.lat.toString(),
            weatherData.city.lon.toString()
        )

        binding.temperatureValue.text = weatherData.temperature.toString()
        binding.feelsLikeValue.text = weatherData.feelsLike.toString()
    }

}