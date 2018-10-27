package com.knacky.earthquake.presentation.dashboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.knacky.earthquake.EarthQuakeApp
import com.knacky.earthquake.R
import com.knacky.earthquake.presentation.dashboard.adapters.DashboardViewPagerAdapter
import com.knacky.earthquake.presentation.dashboard.di.DashboardModule
import com.knacky.earthquake.presentation.dashboard.fragments.EarthQuakeListFragment
import com.knacky.earthquake.presentation.dashboard.fragments.EarthQuakesMapFragment
import kotlinx.android.synthetic.main.dashboard_activity.*
import javax.inject.Inject

class DashboardActivity : AppCompatActivity(), DashboardActivityPresenter.DashboardActivityView {
    lateinit var dashboardViewPagerAdapter: DashboardViewPagerAdapter
    @Inject
    lateinit var dashboardActivityPresenter: DashboardActivityPresenter<DashboardActivityPresenter.DashboardActivityView>
    val dashboardComponent by lazy { EarthQuakeApp.appComponent?.provideDashboardComponent(DashboardModule()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)
        daggerInit()

        requestData()
        initViewPager()
    }



    private fun requestData(){
        dashboardActivityPresenter.getEarthquakeData()
    }

    private fun initViewPager() {
        dashboardViewPagerAdapter = DashboardViewPagerAdapter(supportFragmentManager)
        dashboardViewPagerAdapter.addFragment(EarthQuakeListFragment(), "List")
        dashboardViewPagerAdapter.addFragment(EarthQuakesMapFragment(), "Map")
        dashboard_pager.adapter = dashboardViewPagerAdapter
        dashboard_tabs.setupWithViewPager(dashboard_pager)
    }

    private fun daggerInit(){
        dashboardComponent?.inject(this)
        dashboardActivityPresenter.setView(this)
    }

    override fun onPause() {
        super.onPause()
        dashboardActivityPresenter.destroy()
    }

    override fun onResume() {
        super.onResume()
        dashboardActivityPresenter.setView(this)
    }
}