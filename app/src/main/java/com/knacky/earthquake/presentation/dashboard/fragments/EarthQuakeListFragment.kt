package com.knacky.earthquake.presentation.dashboard.fragments

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knacky.earthquake.R
import com.knacky.earthquake.data.entity.Earthquake
import com.knacky.earthquake.presentation.dashboard.adapters.EarthquakesListAdapter
import kotlinx.android.synthetic.main.earthquake_list_fragment.*

/**
 * Created by knacky on 27.10.2018.
 */
class EarthQuakeListFragment : Fragment() {

    companion object {
        val ALL_EARTHQUAKES_KEY = "allEarthquakesKey"

        fun newInstance(allEarthquakes: List<Earthquake>): EarthQuakeListFragment {
            val list = ArrayList<Earthquake>()
            allEarthquakes.forEach { list.add(it) }

            val fragment = EarthQuakeListFragment()
            val bundle = Bundle()
            Log.i("listFragment", "put putList: $allEarthquakes")
            bundle.putParcelableArrayList(ALL_EARTHQUAKES_KEY, list)

            fragment.arguments = bundle
            return fragment
        }
    }

    val allEarthquakesList by lazy { arguments?.getParcelableArrayList<Earthquake>(ALL_EARTHQUAKES_KEY) }
    private var quakesListAdaapter: EarthquakesListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("listFragment", "getList: $allEarthquakesList")
        return inflater.inflate(R.layout.earthquake_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initQuakesList()
    }

    private fun initQuakesList() {
        quakesListAdaapter = EarthquakesListAdapter(context!!, allEarthquakesList?.toList()!!)
        quake_info_list_rv.layoutManager = LinearLayoutManager(context)
        quake_info_list_rv.adapter = quakesListAdaapter
    }
}