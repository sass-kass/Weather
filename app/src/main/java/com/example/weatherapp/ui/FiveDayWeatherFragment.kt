package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.weatherapp.databinding.FragmentFiveDayWeatherBinding
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class FiveDayWeatherFragment : Fragment() {
    private var _binding: FragmentFiveDayWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FiveDayWeatherViewModel by viewModels()

    private val args: FiveDayWeatherFragmentArgs by navArgs()

    @Inject
    lateinit var adapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFiveDayWeatherBinding.inflate(inflater, container, false)
        val divider = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        binding.fiveDayRecyclerview.addItemDecoration(divider)
        viewModel.setCityName(args.cityName)
        viewModel.cityName.observe(viewLifecycleOwner) {
            viewModel.getWeather()
        }
        binding.fiveDayRecyclerview.adapter = adapter
        viewModel.currentWeather.observe(viewLifecycleOwner) { weather ->
            weather.let {
                adapter.submitList(weather.list)
                binding.cityLabel.text = weather.city.name
            }
        }
        return binding.root
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