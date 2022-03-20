package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.weatherapp.databinding.FragmentFiveDayWeatherBinding
import com.example.weatherapp.repository.WeatherRepository
import java.util.*

class FiveDayWeatherFragment : Fragment() {
    private var _binding: FragmentFiveDayWeatherBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FiveDayWeatherViewModel

    private val args: FiveDayWeatherFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFiveDayWeatherBinding.inflate(inflater, container, false)
        val view = binding.root
        val dataSource = WeatherRepository()
        val viewModelFactory = FiveDayWeatherViewModelFactory(dataSource, args.cityName)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FiveDayWeatherViewModel::class.java)

        val adapter = WeatherAdapter()
        binding.fiveDayRecyclerview.adapter = adapter
        val divider = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        binding.fiveDayRecyclerview.addItemDecoration(divider)
        viewModel.currentWeather.observe(viewLifecycleOwner) { weather ->
            weather.let {
                adapter.submitList(weather.list)
                binding.cityLabel.text = weather.city.name
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Next five day weather"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}