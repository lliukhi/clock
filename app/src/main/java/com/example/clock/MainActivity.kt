package com.example.clock

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager


import com.example.clock.ui.ChronographFragment

import com.example.clock.ui.ClockFragment
import com.example.clock.ui.NonSwipeableViewPager

import com.example.clock.ui.TimePieceFragment
import com.example.clock.ui.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
        private lateinit var viewPager: NonSwipeableViewPager
        private lateinit var viewPagerAdapter: ViewPagerAdapter
        private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.view_pager)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
//        viewPager.adapter = ScreenSlidePagerAdapter(this)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
//        TabLayoutMediator(
//            tabLayout, viewPager
//        ) { tab: TabLayout.Tab, position: Int ->
//            when (position) {
//                0 -> tab.setText("Time")
//                1 -> tab.setText("Alarm")
//                2 -> tab.setText("Timer")
//                3 -> tab.setText("Stopwatch")
//            }
//        }.attach()
//    }

        viewPagerAdapter.addFragment(ClockFragment())
        viewPagerAdapter.addFragment(ClockFragment())
        viewPagerAdapter.addFragment(ChronographFragment())
        viewPagerAdapter.addFragment(TimePieceFragment())
        // 设置适配器
        viewPager.setAdapter(viewPagerAdapter)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // 页面正在被滑动时调用
            }

            override fun onPageSelected(position: Int) {
                // 页面已经被选中时调用
                when (position) {
                    0 -> bottomNavigationView.menu.findItem(R.id.nav_page1).isChecked = true
                    1 -> bottomNavigationView.menu.findItem(R.id.nav_page2).isChecked = true
                    2 -> bottomNavigationView.menu.findItem(R.id.nav_page3).isChecked = true
                    3 -> bottomNavigationView.menu.findItem(R.id.nav_page4).isChecked = true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                // 页面滑动状态改变时调用
            }
        })

        bottomNavigationView = findViewById(R.id.bottom_navigation)
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_page1 -> {viewPager.setCurrentItem(0,false)
//                    }
//                R.id.nav_page2 -> {viewPager.setCurrentItem(2,false)
//                    }
//                R.id.nav_page3 -> {viewPager.setCurrentItem(3,false)
//                    }
//                R.id.nav_page4 -> {viewPager.setCurrentItem(4,false)
//                    }
//            }
//            true
//        }
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_page1 -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.nav_page2 -> {
                    viewPager.currentItem = 1
                    true
                }
                R.id.nav_page3 -> {
                    viewPager.currentItem = 2
                    true
                }
                R.id.nav_page4 -> {
                    viewPager.currentItem = 3
                    true
                }
                else -> false
            }
        }
    }

        // 设置ViewPager页面更改监听器

//    private inner  class ScreenSlidePagerAdapter(fa: FragmentActivity?) : FragmentStateAdapter(fa!!) {
//        override fun createFragment(position: Int): Fragment {
//            return when (position) {
//                0 -> ClockFragment()
//                1 -> ClockFragment()
//                2 -> ChronographFragment()
//                3 -> TimePieceFragment()
//                else -> ClockFragment()
//            }
//        }
//
//        override fun getItemCount(): Int {
//            return 4 // 标签数量
//        }
//    }
}