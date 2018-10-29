package com.knacky.earthquake.presentation.dashboard.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.knacky.earthquake.R
import com.knacky.earthquake.data.entity.Earthquake
import com.knacky.earthquake.presentation.dashboard.adapters.EarthquakesListAdapter
import kotlinx.android.synthetic.main.earthquake_map_fragment.*

/**
 * Created by knacky on 27.10.2018.
 */
class EarthQuakesMapFragment: Fragment(), OnMapReadyCallback {


    companion object {
        val ALL_EARTHQUAKES_KEY = "allEarthquakesKey"

        fun newInstance(allEarthquakes: List<Earthquake>): EarthQuakesMapFragment {
            val list = ArrayList<Earthquake>()
            allEarthquakes.forEach { list.add(it) }

            val fragment = EarthQuakesMapFragment()
            val bundle = Bundle()
            Log.i("map", "put putList: $allEarthquakes")
            bundle.putParcelableArrayList(ALL_EARTHQUAKES_KEY, list)

            fragment.arguments = bundle
            return fragment
        }
    }

    private val allEarthquakesList by lazy { arguments?.getParcelableArrayList<Earthquake>(ALL_EARTHQUAKES_KEY) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("mapFragment", "getList: $allEarthquakesList")
        return inflater.inflate(R.layout.earthquake_map_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(map_view != null){
            map_view.onCreate(null)
            map_view.onResume()
            map_view.onLowMemory()
            map_view.getMapAsync(this)
        }

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        MapsInitializer.initialize(context)
        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
        allEarthquakesList?.forEach {
            val lat = it.latitude.toDouble()
            val lng= it.longitude.toDouble()

            googleMap?.addMarker(MarkerOptions().position(LatLng(lat, lng))
                    .title(it.magnitude))
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map_view.onLowMemory()
    }
}