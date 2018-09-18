package com.betomaluje.android.weathertest

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.betomaluje.android.weathertest.adapters.ViewPagerAdapter
import com.betomaluje.android.weathertest.utils.DepthPageTransformer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager(viewPager)
    }

    private fun setupViewPager(pager: ViewPager) {
        pager.setPageTransformer(true, DepthPageTransformer())

        val adapter = ViewPagerAdapter(supportFragmentManager)

        val cities = HashMap<String, String>()
        cities.put("890869", "Gothenburg")
        cities.put("906057", "Stockholm")
        cities.put("2455920", "Mountain View")
        cities.put("44418", "London")
        cities.put("2459115", "New York")
        cities.put("638242", "Berlin")

        val titles = ArrayList<String>()

        for ((key, value) in cities) {
            val fragment = WeatherFragment.newInstance(key)
            adapter.addFragment(fragment)
            titles.add(value)
        }

        pager.adapter = adapter

        tabLayout.setupWithViewPager(pager, true);

        toolbar.title = titles.get(0)

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                toolbar.title = titles.get(position)
            }
        })
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = (viewPager.currentItem - 1)
        }
    }
}
