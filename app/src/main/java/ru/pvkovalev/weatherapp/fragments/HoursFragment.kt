package ru.pvkovalev.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import org.json.JSONArray
import org.json.JSONObject
import ru.pvkovalev.weatherapp.MainViewModel
import ru.pvkovalev.weatherapp.adapters.WeatherAdapter
import ru.pvkovalev.weatherapp.adapters.WeatherModel
import ru.pvkovalev.weatherapp.databinding.FragmentHoursBinding


class HoursFragment : Fragment() {

    private var _binding: FragmentHoursBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoursBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            adapter.submitList(getHoursList(it))
        }
    }

    private fun initRecyclerView() = with(binding) {
        adapter = WeatherAdapter(null)
        rcView.adapter = adapter

    }

    private fun getHoursList(wItem: WeatherModel): List<WeatherModel> {
        val hoursArray = JSONArray(wItem.hours)
        val list = ArrayList<WeatherModel>()
        for (i in 0 until hoursArray.length()) {
            val item = WeatherModel(
                wItem.city,
                (hoursArray[i] as JSONObject)
                    .getString("time"),
                (hoursArray[i] as JSONObject)
                    .getJSONObject("condition")
                    .getString("text"),
                (hoursArray[i] as JSONObject)
                    .getString("temp_c"),
                "",
                "",
                (hoursArray[i] as JSONObject)
                    .getJSONObject("condition")
                    .getString("icon"),
                ""
                )
            list.add(item)
        }
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}